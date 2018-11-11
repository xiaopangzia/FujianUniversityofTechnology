package com.cheng.shop.cart.dao;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import com.cheng.shop.book.domain.Book;
import com.cheng.shop.cart.domain.CartItem;
import com.cheng.shop.user.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartItemDao {
    private QueryRunner queryRunner = new TxQueryRunner();

    /**
     * 把一个Map映射成CartItem
     *
     * @param map
     * @return
     */
    private CartItem toCartItem(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        CartItem cartItem = CommonUtils.toBean(map, CartItem.class);
        Book book = CommonUtils.toBean(map, Book.class);
        User user = CommonUtils.toBean(map, User.class);
        cartItem.setBook(book);
        cartItem.setUser(user);
        return cartItem;
    }

    /**
     * 把多个Map映射成CartItem(List<CartItem>)
     *
     * @param mapList
     * @return
     */
    private List<CartItem> toCartItemList(List<Map<String, Object>> mapList) {
        List<CartItem> cartItemList = new ArrayList<CartItem>();
        for (Map<String, Object> map : mapList) {
            CartItem cartItem = toCartItem(map);
            cartItemList.add(cartItem);
        }
        return cartItemList;
    }

    /**
     * 用来生成where语句
     *
     * @param len
     * @return
     */
    private String toWhereSql(int len) {
        StringBuilder stringBuilder = new StringBuilder("cartItemId in(");
        for (int i = 0; i < len; i++) {
            stringBuilder.append("?");
            if (i < len - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /**
     * 通过用户查询购物车条目
     *
     * @param uid
     * @return
     * @throws SQLException
     */
    public List<CartItem> findByUser(String uid) throws SQLException {
        String sql = "select * from t_cartitem c,t_book b where c.bid=b.bid and uid=?";
        List<Map<String, Object>> mapList = queryRunner.query(sql, new MapListHandler(), uid);
        return toCartItemList(mapList);
    }

    /**
     * 加载多个CartItem
     * @param cartItemIds
     * @throws SQLException
     * @return
     */
    public List<CartItem> loadCartItems(String cartItemIds) throws SQLException {

        // 1.把cartItems转换成数组
        Object[] cartItemIdArray = cartItemIds.split(",");
        // 2.生成where子句
        String whereSql = toWhereSql(cartItemIdArray.length);
        // 3.生成sql语句
        String sql="select * from t_cartitem c,t_book b where c.bid=b.bid and "+whereSql;
        // 4.执行sql，返回List<CartItem>
        return toCartItemList(queryRunner.query(sql, new MapListHandler(),cartItemIdArray));
    }

    /**
     * 通过id查询
     * @param cartItemId
     * @throws SQLException
     * @return
     */
    public CartItem findByCartItemId(String cartItemId) throws SQLException {
        String sql = "select * from t_cartitem c,t_book b where c.bid=b.bid and c.cartItemId=?";
        Map<String, Object> map = queryRunner.query(sql, new MapHandler(), cartItemId);
        return toCartItem(map);
    }

    /**
     * 批量删除
     * @param cartItemIds
     * @throws SQLException
     */
    public void batchDelete(String cartItemIds) throws SQLException {
        // 1.把cartItems转换成数组
        Object[] cartItemIdArray = cartItemIds.split(",");
        // 2.生成where子句
        String whereSql = toWhereSql(cartItemIdArray.length);
        // 3.生成sql语句
        String sql="delete from t_cartitem where "+whereSql;
        // 4.执行sql
        queryRunner.update(sql,cartItemIdArray);//其中cartItemIdArray必须是Object类型的数组！
    }

    /**
     * 查询某个用户的某本图书的购物车条目是否存在
     * @param uid
     * @param bid
     * @return
     * @throws SQLException
     */
    public CartItem findByUidAndBid(String uid, String bid) throws SQLException {
        String sql = "select * from t_cartitem where uid=? and bid=?";
        Map<String, Object> map = queryRunner.query(sql, new MapHandler(), uid, bid);
        CartItem cartItem = toCartItem(map);
        return cartItem;
    }

    /**
     * 修改指定条目的数量
     * @param cartItemId
     * @param quantity
     * @throws SQLException
     */
    public void updateQuantity(String cartItemId, int quantity) throws SQLException {
        String sql = "update t_cartitem set quantity=? where cartItemId=?";
        queryRunner.update(sql, quantity, cartItemId);
    }

    /**
     * 添加条目
     * @param cartItem
     * @throws SQLException
     */
    public void addCartItem(CartItem cartItem) throws SQLException {
        String sql = "insert into t_cartitem(cartItemId,quantity,bid,uid)" + " values(?,?,?,?)";
        Object[] params = {
                cartItem.getCartItemId(),
                cartItem.getQuantity(),
                cartItem.getBook().getBid(),
                cartItem.getUser().getUid()
        };
        queryRunner.update(sql, params);
    }

}
