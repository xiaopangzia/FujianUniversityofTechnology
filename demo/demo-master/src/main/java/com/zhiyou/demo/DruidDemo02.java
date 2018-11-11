package com.zhiyou.demo;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidDemo02 {

    private static DataSource ds;

    static {
        try {
            InputStream in = DruidDemo02.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties props = new Properties();
            props.load(in);
            ds = DruidDataSourceFactory.createDataSource(props);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = ds.getConnection();
        // 执行SQL操作
        // .....
        // 关闭数据库连接
        conn.close();
    }
}
