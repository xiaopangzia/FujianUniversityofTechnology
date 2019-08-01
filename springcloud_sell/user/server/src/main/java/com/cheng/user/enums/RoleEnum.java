package com.cheng.user.enums;

import lombok.Getter;

/**
 * @author cheng
 * @date 2019-07-28
 * @description
 */
@Getter
public enum RoleEnum {

    BUYER(1, "买家"),
    SELLER(2, "卖家"),
    ;

    private Integer code;

    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
