package com.cheng.weather.config;

import com.cheng.weather.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-15
 * Time: 上午6:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityConfigTest {

    @Autowired
    private CityConfig cityConfig;

    @Test
    public void cityConfigTest() {

        Assert.assertEquals("https://jisutqybmf.market.alicloudapi.com",
                cityConfig.getHost());
        Assert.assertEquals("6a3aa81479f346fa848b4d48099ca886",
                cityConfig.getAppCode());
        Assert.assertEquals("GET",
                cityConfig.getMethod());
        Assert.assertEquals("/weather/city",
                cityConfig.getCity());
        Assert.assertEquals("/weather/query",
                cityConfig.getWeather());

    }

    @Test
    public void getCityTest() {

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + cityConfig.getAppCode());

        Map<String, String> querys = new HashMap<String, String>();

        try {
            HttpResponse response = HttpUtils.doGet(
                    cityConfig.getHost(),
                    cityConfig.getCity(),
                    cityConfig.getMethod(),
                    headers, querys);
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
