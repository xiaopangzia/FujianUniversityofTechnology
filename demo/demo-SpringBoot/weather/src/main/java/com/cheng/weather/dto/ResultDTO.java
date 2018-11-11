package com.cheng.weather.dto;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-17
 * Time: 下午12:03
 */
@Data
public class ResultDTO {

    private String status;

    private String msg;

    private WeatherDTO result;
}
