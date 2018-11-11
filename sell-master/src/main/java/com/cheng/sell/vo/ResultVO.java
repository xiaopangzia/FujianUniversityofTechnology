package com.cheng.sell.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * http请求返回的最外层对象
 * View Object 视图对象
 * @author cheng
 * Date: 2018-07-01
 * Time: 下午8:49
 */
@Data
public class ResultVO<T> implements Serializable {


    private static final long serialVersionUID = 2057461327824703749L;

    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 具体内容 */
    private T data;

}
