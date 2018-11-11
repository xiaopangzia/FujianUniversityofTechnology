package com.cheng.weather.service.impl;

import com.cheng.weather.dataobject.City;
import com.cheng.weather.dto.ResultDTO;
import com.cheng.weather.dto.WeatherDTO;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-17
 * Time: 上午10:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherDTOServiceImplTest {

    @Autowired
    private WeatherServiceImpl weatherService;

    @Test
    public void getWeather() throws IOException {

        City city = new City(33,null,null,null);
        ResultDTO resultDTO = weatherService.getWeather(city);
        System.out.println(resultDTO);

    }
}
