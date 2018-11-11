package com.cheng.weather.dto;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-17
 * Time: 上午11:06
 */
@Data
public class WeatherDTO {

    private String city;
    private String cityid;
    private String citycode;
    private String date;
    private String week;
    private List<HourlyDTO> hourly;



}
