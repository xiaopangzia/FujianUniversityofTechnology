package com.cheng.weather.vo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-15
 * Time: 下午1:50
 */
@Data
public class ResultVO<T> {

    private String status;

    private String msg;

    private T result;

}
