package com.cheng.shop.order.dao;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import com.cheng.shop.book.domain.Book;
import com.cheng.shop.order.domain.OrderItem;
import com.cheng.shop.order.domain.Order;
import com.cheng.shop.pager.Expression;
import com.cheng.shop.pager.PageBean;
import com.cheng.shop.pager.PageConstants;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDao {
    private QueryRunner queryRunner = new TxQueryRunner();

    // 通用查询方法
    public PageBean<Order> findByCriteria(List<Expression> expressionList, int pc) throws SQLException {

        /**
         * 1.得到ps
         * 2.得到tr
         * 3.得到beanList
         * 4.创建pageBean，返回
         */

        // 1.得到ps
        int ps = PageConstants.ORDER_PAGE_SIZE;

        // 2.通过exprList来生成where子句
        StringBuilder whereSql = new StringBuilder(" where 1=1");
        List<Object> params = new ArrayList<Object>();//SQL中有问号，它对应问号的值
        for (Expression expr : expressionList) {
            /**
             * 添加一个条件
             * 1.以and开头
             * 2.条件的名称
             * 3.条件运算符
             * 4.如果条件不是is null 再追加问好，在向params添加问好对应的值
             */
            whereSql.append(" and ").append(expr.getName())
                    .append(" ").append(expr.getOperator()).append(" ");
            //where 1=1 and bid =

            if (!expr.getOperator().equals("is null")) {
                whereSql.append("?");
                params.add(expr.getValue());
            }
        }

        // 3.总记录数
        String sql = "select count(*) from t_order" + whereSql;
        Number number = (Number) queryRunner.query(sql, new ScalarHandler(), params.toArray());
        int tr = number.intValue();//得到总记录数

        // 4.得到beanList，即当前记录数
        sql = "select * from t_order" + whereSql + " order by ordertime desc limit ?,?";
        params.add((pc - 1) * ps);//第一个问好：（页数-1）*8,当前页首行记录下标
        params.add(ps);//一共查询几行，就是每页记录数

        List<Order> beanList = queryRunner.query(sql, new BeanListHandler<Order>(Order.class), params.toArray());
        //虽然已经获取所有订单，但是每个订单中并没有订单条目
        //遍历每个订单，为其加载它的所有订单条目
        for (Order order: beanList) {
            loadOrderItem(order);
        }

        // 5.创建PageBean，设置参数
        PageBean<Order> pb = new PageBean<Order>();
        // 其中PageBean没有url，这个任务由Servlet完成
        pb.setBeanList(beanList);
        pb.setPc(pc);
        pb.setPs(ps);
        pb.setTr(tr);

        return pb;
    }

    //加载指定OrderItem
    private void loadOrderItem(Order order) throws SQLException {
        String sql = "select * from t_orderitem where oid=?";
        List<Map<String,Object>> mapList=queryRunner.query(sql, new MapListHandler(), order.getOid());
        List<OrderItem> orderItemList = toOrderItemList(mapList);

        order.setOrderItemList(orderItemList);
    }

    /**
     * 把多个map转换成OrderItem
     * @param mapList
     * @return
     */
    private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        for (Map<String, Object> map : mapList) {
            OrderItem orderItem = toOrderItem(map);
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    /**
     * 把一个map转换成一个OrderItem
     * @param map
     * @return
     */
    private OrderItem toOrderItem(Map<String, Object> map) {
        OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
        Book book = CommonUtils.toBean(map, Book.class);
        orderItem.setBook(book);
        return orderItem;
    }

    // 按用户查询订单
    public PageBean<Order> findByUid(String uid, int pc) throws SQLException {
        List<Expression> expressionList = new ArrayList<Expression>();
        expressionList.add(new Expression("uid", "=", uid));
        return findByCriteria(expressionList, pc);
    }

    /**
     * 生成订单
     * @param order
     * @throws SQLException
     */
    public void add(Order order) throws SQLException {
        // 1.插入订单
        String sql = "insert into t_order values(?,?,?,?,?,?)";
        Object[] params = {
                order.getOid(),
                order.getOrdertime(),
                order.getTotal(),
                order.getStatus(),
                order.getAddress(),
                order.getOwner().getUid()
        };
        queryRunner.update(sql, params);

        /**
         * 2. 循环遍历订单的所有条目，让每个条目生成一个Object
         *    多个条目就对应Object[][]
         *    执行批处理，完成插入订单条目
         */
        sql = "insert into t_orderitem values(?,?,?,?,?,?,?,?)";
        int len = order.getOrderItemList().size();
        Object[][] objects = new Object[len][];
        for (int i = 0; i < len; i++) {
            OrderItem item = order.getOrderItemList().get(i);
            objects[i] = new Object[]{
                    item.getOrderItemId(),
                    item.getQuantity(),
                    item.getSubtotal(),
                    item.getBook().getBid(),
                    item.getBook().getBname(),
                    item.getBook().getCurrPrice(),
                    item.getBook().getImage_b(),
                    order.getOid()
            };
        }
        queryRunner.batch(sql, objects);
    }

    /**
     * 加载订单条目
     * @param oid
     * @return
     * @throws SQLException
     */
    public Order load(String oid) throws SQLException {
        String sql = "select * from t_order where oid=?";
        Order order = queryRunner.query(sql, new BeanHandler<Order>(Order.class), oid);
        loadOrderItem(order);
        return order;
    }

    /**
     * 查询订单状态
     * @param oid
     * @return
     * @throws SQLException
     */
    public int findStatus(String oid) throws SQLException {
        String sql = "select status from t_order where oid=?";
        Number number = (Number) queryRunner.query(sql, new ScalarHandler(), oid);
        return number.intValue();
    }

    /**
     * 修改订单状态
     * @param oid
     * @param status
     * @throws SQLException
     */
    public void updateStatus(String oid, int status) throws SQLException {
        String sql = "update t_order set status=? where oid=?";
        queryRunner.update(sql, status, oid);
    }

    /**
     * 按状态查询
     * @param status
     * @param pc
     * @return
     */
    public PageBean<Order> findByStatus(int status, int pc) throws SQLException {
        List<Expression> exprList = new ArrayList<Expression>();
        exprList.add(new Expression("status", "=", status + ""));
        return findByCriteria(exprList, pc);
    }

    /**
     * 查询所有
     * @param pc
     * @return
     */
    public PageBean<Order> findAll(int pc) throws SQLException {
        List<Expression> exprList = new ArrayList<Expression>();
        return findByCriteria(exprList, pc);
    }
}
