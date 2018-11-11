package com.zhiyou;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import com.zhiyou.util.HttpUtil;

public class AppWeather {

    public static void main(String[] args) {
        String host = "http://jisutqybmf.market.alicloudapi.com";
        String path = "/weather/query";
        String method = "GET";
        String appcode = "bf3158b6a9da4bb68dfae10971ef9414";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("city", "安顺");
        querys.put("citycode", "101260301");
        querys.put("cityid", "111");
        querys.put("ip", "ip");
        querys.put("location", "location");
        try {
            HttpResponse response = HttpUtil.doGet(host, path, method, headers, querys);
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
