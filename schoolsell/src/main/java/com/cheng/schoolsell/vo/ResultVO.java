package com.cheng.schoolsell.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-04
 * Time: 上午10:11
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -1027164835337652162L;

    private Integer code;

    private String msg;

    private T data;

}
