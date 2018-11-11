package com.cheng.sell.enums;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 商品状态
 * @author cheng
 * Date: 2018-07-01
 * Time: 下午8:01
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {

    /** 0状态码,在架 */
    UP(0, "在架"),

    /** 1状态码,下架 */
    DOWN(1,"下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
