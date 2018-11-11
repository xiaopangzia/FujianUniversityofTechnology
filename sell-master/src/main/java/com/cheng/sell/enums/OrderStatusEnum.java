package com.cheng.sell.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 订单状态枚举类
 *
 * @author cheng
 * Date: 2018-07-02
 * Time: 上午8:05
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {

    /**
     * 0,状态码 新订单
     */
    NEW(0, "新订单"),

    /**
     * 1,状态码 已完结
     */
    FINISHED(1, "已完结"),

    /**
     * 2,状态码 已取消
     */
    CANCEL(2, "已取消"),;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
