package com.cheng.schoolsell.handler;

import com.cheng.schoolsell.exception.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-06
 * Time: 下午9:03
 */
@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ModelAndView BusinessHandle(BusinessException e) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("msg", e.getMessage());
        map.put("url", e.getUrl());
        return new ModelAndView("business/common/error",map);
    }

}
