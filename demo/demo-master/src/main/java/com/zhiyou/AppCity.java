package com.zhiyou;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.zhiyou.model.City;
import com.zhiyou.model.RCity;
import com.zhiyou.util.DBUtil;
import com.zhiyou.util.HttpUtil;


public class AppCity {

    public static void main(String[] args) {
        String host = "http://jisutqybmf.market.alicloudapi.com";// 获取天气的接口地址
        String path = "/weather/city";
        String method = "GET";
        String appcode = "bf3158b6a9da4bb68dfae10971ef9414";// 用户的认证信息
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();

        try {
            HttpResponse response = HttpUtil.doGet(host, path, method, headers, querys);
            String resultJson = EntityUtils.toString(response.getEntity());
            RCity r = JSON.parseObject(resultJson, RCity.class);
            // 获取到城市信息列表
            List<City> list = r.getResult();
            // 2.获取数据库连接
            Connection conn = DBUtil.getConnection();
            // 3.获取执行SQL对象
            String sql = "INSERT INTO t_city(cityid,citycode,city) VALUES(?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            for (City city : list) {
                // 只打印省会城市的信息

                    pst.setString(1, city.getCityid());
                    pst.setString(2, city.getCitycode());
                    pst.setString(3, city.getCity());
                    // 执行插入
                    pst.executeUpdate();

            }
            // 5.关闭数据库连接释放资源
            DBUtil.close(conn, pst);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
