package com.cheng.weather.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-17
 * Time: 上午11:01
 */
@Data
public class HourlyDTO {

    private String time;

    private String weather;

    private String temp;

    private String img;

}
