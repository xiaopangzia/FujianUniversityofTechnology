package com.cheng.sell.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-02
 * Time: 上午8:10
 */
@Getter
public enum PayStatusEnum implements CodeEnum{

    /** 0状态码,未支付 */
    WAIT(0, "未支付"),

    /** 1状态码,支付成功 */
    SUCCESS(1, "支付成功"),
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
