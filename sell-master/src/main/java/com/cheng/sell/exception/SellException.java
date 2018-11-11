package com.cheng.sell.exception;

import com.cheng.sell.enums.ResultEnum;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 异常处理
 * @author cheng
 * Date: 2018-07-03
 * Time: 上午8:07
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        // 把message传到父类的构造方法去
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
