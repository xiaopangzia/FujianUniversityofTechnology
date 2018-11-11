package com.zhiyou.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.zhiyou.util.DBUtil;

public class JDBCDemo03 {

    public static void main(String[] args) throws Exception {
        // 1.获取数据库连接
        Connection conn = DBUtil.getConnection();
        // 2.获取执行SQL对象
        String sql = "INSERT INTO t_city(cityid,citycode,city) VALUES(?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "001");
        pst.setString(2, "001");
        pst.setString(3, "北京");
        // 执行插入，然会影响的条数
        // 3.执行sql获取结果
        int result = pst.executeUpdate();
        System.out.println(result);
        // 4.关闭数据库连接释放资源
        DBUtil.close(conn, pst);
    }
}
