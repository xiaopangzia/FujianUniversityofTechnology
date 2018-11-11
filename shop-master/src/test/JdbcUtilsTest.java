import cn.itcast.jdbc.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtilsTest {

    @Test
    public void testGetConnection() throws SQLException {
        Connection con=JdbcUtils.getConnection();
        System.out.println(con);
        JdbcUtils.releaseConnection(con);
        System.out.println(con.isClosed());
    }

    @Test
    public void testTransaction() throws Exception {
        try{
            JdbcUtils.beginTransaction();//开启事物

            //操作
            JdbcUtils.commitTransaction();//提交事务
        }catch(Exception e){
            JdbcUtils.rollbackTransaction();//回滚事务
        }
    }

}
