package com.cheng.schoolsell.exception;

import com.cheng.schoolsell.enums.UserResultVOEnum;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-05
 * Time: 上午10:23
 * 自定义一个用户异常
 */
@Getter
public class UserException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code;

    public UserException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public UserException(UserResultVOEnum userResultVOEnum) {
        super(userResultVOEnum.getMessage());
        this.code = userResultVOEnum.getCode();
    }
}
