import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.JdbcUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/*
 *TxQueryRunner类是common-dbutils下QueryRunner类的子类
 * 简化JDBC操作
 * update()=>insert update delete
 * query()=>select
 * batch()=>批处理
 */
public class TxQueryRunnerTest {

    @Test
    public void testUpdate() throws SQLException {
        String sql="insert into person values(?,?,?)";
        Object[] params={CommonUtils.uuid(),"hhh",22};

        QueryRunner qr=new TxQueryRunner();
        qr.update(sql,params);
    }

    @Test
    public void testUpdate2() throws Exception {

        try{
            JdbcUtils.beginTransaction();//开启事物

            String sql="insert into person values(?,?,?)";
            Object[] params={CommonUtils.uuid(),"qqq",22};
            QueryRunner qr=new TxQueryRunner();
            qr.update(sql,params);

            if(false){
                throw new Exception();
            }
            params=new Object[]{CommonUtils.uuid(),"www",22};
            qr.update(sql,params);
            //操作
            JdbcUtils.commitTransaction();//提交事务
        }catch(Exception e){
            try{
                JdbcUtils.rollbackTransaction();//回滚事务
            }catch (SQLException e1){

            }
            throw e;
        }
    }

    /*
     *单行结果集javabean
     */
    @Test
    public void testQuery() throws SQLException {
        String sql="select * from person where name=?";
        QueryRunner qr=new TxQueryRunner();

        person p=qr.query(sql,new BeanHandler<person>(person.class),"www");
        System.out.println(p);
    }

    /*
     *多行结果集javabean
     * BeanListHendler
     */
    @Test
    public void testQuery1() throws SQLException {
        String sql="select * from person";
        QueryRunner qr=new TxQueryRunner();

        List<person> list=qr.query(sql,new BeanListHandler<person>(person.class));
        System.out.println(list);
    }

    /*
     *多行结果集封装map
     * MapListHandle
     * 一行对应一个map 多行对应List<map>
     */
    @Test
    public void testQuery3() throws SQLException {
        String sql="select * from person";
        QueryRunner qr=new TxQueryRunner();

        List<Map<String,Object>> mapList=qr.query(sql,new MapListHandler());
        System.out.println(mapList);
    }

    /*
     *ScalarHandle
     *单行单列
     */
    @Test
    public void testQuery5() throws SQLException {
        String sql="select * from person where name=?";
        QueryRunner qr=new TxQueryRunner();

        Object obj=qr.query(sql,new ScalarHandler(),"www");
        /*
         *先number类型
         * 再转别的类型
         */
        Number number=(Number)obj;
        String qqq=number.toString();
        System.out.println(qqq);
    }
}
