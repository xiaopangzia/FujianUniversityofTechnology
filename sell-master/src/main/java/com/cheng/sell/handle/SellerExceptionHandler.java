package com.cheng.sell.handle;

import com.cheng.sell.exception.SellerAuthorizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-12
 * Time: 下午5:08
 */
@ControllerAdvice
public class SellerExceptionHandler {

    // 拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat("http://127.0.0.1:8080")
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat("http://127.0.0.1:8080/sell/seller/login")
                .concat("?openid=abc"));
    }

}
