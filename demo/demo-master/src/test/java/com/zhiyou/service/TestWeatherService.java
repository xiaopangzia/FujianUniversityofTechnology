package com.zhiyou.service;

import java.util.List;
import org.junit.Test;
import com.zhiyou.model.Hourly;
import com.zhiyou.model.RWeather;
import com.zhiyou.model.Weather;

public class TestWeatherService {

    WeatherService service = new WeatherService();

    @Test
    public void test() throws Exception {
        RWeather r = service.getWeatherInfo("安顺", "101260301", "111");
        // 获取天气情况
        Weather w = r.getResult();
        System.out.println(w.getCity() + " " + w.getCityid() + " " + w.getCitycode());
        System.out.println(w.getDate() + " " + w.getWeek());
        // 获取每小时的天气信息
        List<Hourly> list = w.getHourly();
        for (Hourly h : list) {
            System.out.println("---------------------");
            System.out.println(h.getTime() + " " + h.getTemp() + "℃   " + h.getWeather());
        }
    }
}
