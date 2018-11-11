package com.zhiyou.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDemo01 {

    public static void main(String[] args) throws Exception {
        // 1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2.获取数据库连接
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/weather?useUnicode=true&characterEncoding=UTF-8", "root", "739237663");
        // 3.获取执行SQL对象
        Statement st = conn.createStatement();
        String sql = "INSERT INTO t_city(cityid,citycode,city) VALUES('002','001','北京')";
        // 执行插入，然会影响的条数
        // 4.执行sql获取结果
        int result = st.executeUpdate(sql);
        System.out.println(result);
        // 5.关闭数据库连接释放资源
        st.close();
        conn.close();
    }
}

