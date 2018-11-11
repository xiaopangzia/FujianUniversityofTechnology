package com.zhiyou.demo;
import java.sql.Connection;
import java.sql.SQLException;
import com.alibaba.druid.pool.DruidDataSource;
public class DruidDemo01 {

    private static DruidDataSource ds;

    static {
        // 创建Druid的数据源对象
        ds = new DruidDataSource();
        // 设置用户名
        ds.setUsername("root");
        // 设置密码
        ds.setPassword("739237663");
        // 设置数据库连接
        ds.setUrl("jdbc:mysql://155.94.247.112:3306/");
        // 设置驱动类型
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        // 最大连接池数量
        ds.setMaxActive(100);
    }
    public static void main(String[] args) throws SQLException {
        Connection conn = ds.getConnection();
        // 执行SQL操作
        // .....
        // 关闭数据库连接
        conn.close();
    }
}
