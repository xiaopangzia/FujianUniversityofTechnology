package com.cheng.weather.service;

import com.cheng.weather.dataobject.City;
import com.cheng.weather.dto.ResultDTO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-17
 * Time: 上午10:30
 */
public interface WeatherService {

    ResultDTO getWeather(City city);

}
