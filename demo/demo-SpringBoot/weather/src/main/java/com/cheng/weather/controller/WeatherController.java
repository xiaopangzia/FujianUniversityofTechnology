package com.cheng.weather.controller;

import com.cheng.weather.dataobject.City;
import com.cheng.weather.dto.HourlyDTO;
import com.cheng.weather.dto.ResultDTO;
import com.cheng.weather.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-17
 * Time: 上午9:15
 */
@RestController
@Slf4j
@CrossOrigin
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public Map<String,Object> getWeather(@RequestParam("cid") Integer cid) {
        City city = new City(cid, null, null, null);
        ResultDTO resultDTO = weatherService.getWeather(city);
        List<String> times = new ArrayList<>();
        List<String> temps = new ArrayList<>();
        List<HourlyDTO> hourlyDTOS = resultDTO.getResult().getHourly();
        for (HourlyDTO hourlyDTO : hourlyDTOS) {
            times.add(hourlyDTO.getTime());
            temps.add(hourlyDTO.getTemp());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("times", times);
        map.put("temps", temps);
        return map;
    }

}
