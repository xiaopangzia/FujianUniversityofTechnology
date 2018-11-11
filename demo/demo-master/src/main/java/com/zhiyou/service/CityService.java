package com.zhiyou.service;

import com.zhiyou.model.City;
import com.zhiyou.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityService {
    /**
     * 获取所有城市信息
     *
     * @return
     * @throws SQLException
     */
    public List<City> getAllCity() throws SQLException {
        List<City> list = new ArrayList<City>();
        // 1.获取数据库连接
        Connection conn = DBUtil.getConnection();
        // 2.执行查询sql获取数据
        String sql = "select * from t_city";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            String cityid = rs.getString("cityid");
            String citycode = rs.getString("citycode");
            String city = rs.getString("city");
            // 每查询到一条城市信息放入到集合中
            // 3.将获取到的数据封装到City类的实例上
            // 4.将city类的所有实例放到集合中
            list.add(new City(cityid, citycode, null, city));
        }
        // 5.释放资源
        DBUtil.close(conn, st, rs);
        // 6.返回结果集
        return list;
    }
    /**
     * 通过cityid查询城市的详细信息
     *
     * @param cityid
     * @return
     * @throws Exception
     */
    public City getCityById(String cityid) throws Exception {
        // 1.获取数据库连接
        Connection conn = DBUtil.getConnection();
        // 2.获取数据库的执行器
        String sql = "SELECT * FROM t_city WHERE cityid = ? ";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, cityid);
        ResultSet rs = pst.executeQuery();
        City c = null;
        while (rs.next()) {
            String cityid_ = rs.getString("cityid");
            String citycode = rs.getString("citycode");
            String city = rs.getString("city");
            c = new City(cityid_, citycode, null, city);
            break;
        }
        return c;
    }


}
