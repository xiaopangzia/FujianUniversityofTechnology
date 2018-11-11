package com.zhiyou.demo;

import java.sql.Connection;
import java.sql.Statement;

import com.zhiyou.util.DBUtil;

public class JDBCDemo02 {

    public static void main(String[] args) throws Exception {
        // 2.获取数据库连接
        Connection conn = DBUtil.getConnection();
        // 3.获取执行SQL对象
       Statement st = conn.createStatement();
        String sql = "INSERT INTO t_city(cityid,citycode,city) VALUES('002','001','北京')";
        // 执行插入，然会影响的条数
        // 4.执行sql获取结果
        int result = st.executeUpdate(sql);
        System.out.println(result);
        // 5.关闭数据库连接释放资源
        DBUtil.close(conn, st);
    }
}
