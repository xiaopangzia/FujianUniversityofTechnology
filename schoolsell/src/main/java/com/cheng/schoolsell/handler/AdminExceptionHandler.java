package com.cheng.schoolsell.handler;

import com.cheng.schoolsell.exception.AdminException;
import com.cheng.schoolsell.utils.AdminResultMapUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-06
 * Time: 下午9:03
 */
@ControllerAdvice
public class AdminExceptionHandler {

    @ExceptionHandler(value = AdminException.class)
    public ModelAndView adminHandler(AdminException e) {
        return new ModelAndView("admin/common/error", AdminResultMapUtil.error(e));
    }

}
