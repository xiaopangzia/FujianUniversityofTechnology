package com.zhiyou.service;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import com.zhiyou.model.RWeather;
import com.zhiyou.util.HttpUtil;
import com.zhiyou.util.PropUtil;

/**
 * 获取天气信息服务类
 *
 * @author jack
 *
 */
public class WeatherService {

    private static final String HOST;
    private static final String PATH;
    private static final String APPCODE;

    static {
        HOST = PropUtil.getProperty("alibaba.api.weather.host");
        PATH = PropUtil.getProperty("alibaba.api.weather.query.path");
        APPCODE = PropUtil.getProperty("alibaba.api.appcode");
    }

    /**
     * 根据城市信息获取该城市对应的天气信息
     *
     * @param city
     *            城市名称
     * @param citycode
     *            城市编码
     * @param cityid
     *            城市id
     * @return
     * @throws Exception
     */
    public RWeather getWeatherInfo(String city, String citycode, String cityid) throws Exception {
        // 请求头信息
        Map<String, String> headers = new HashMap<String, String>();
        // 将认证信息放入我们请求的消息头
        headers.put("Authorization", "APPCODE " + APPCODE);
        // 封装查询条件
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("city", city);
        querys.put("citycode", citycode);
        querys.put("cityid", cityid);
        try {
            HttpResponse response = HttpUtil.doGet(HOST, PATH, "GET", headers, querys);
            String jsonResult = EntityUtils.toString(response.getEntity());
            RWeather r = JSON.parseObject(jsonResult, RWeather.class);
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}