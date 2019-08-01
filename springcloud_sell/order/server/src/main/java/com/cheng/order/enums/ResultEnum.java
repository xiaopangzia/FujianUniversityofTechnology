package com.cheng.order.enums;

import lombok.Getter;

/**
 * Created by 廖师兄
 * 2017-12-10 17:32
 * @author cheng
 */
@Getter
public enum ResultEnum {
    PARAM_ERROR(1, "参数错误"),
    CART_EMPTY(2, "购物车为空"),
    ORDER_NOT_EXIST(3, "订单不存在"),
    ORDER_STATUS_ERROR(4, "订单状态错误"),
    ;

    private Integer code;

    private String message;

    /**
     * @param code
     * @param message
     */
    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
