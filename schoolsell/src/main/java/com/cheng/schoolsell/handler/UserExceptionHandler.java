package com.cheng.schoolsell.handler;

import com.cheng.schoolsell.exception.UserException;
import com.cheng.schoolsell.exception.UserNoLoginException;
import com.cheng.schoolsell.utils.ResultVoUtil;
import com.cheng.schoolsell.vo.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-06
 * Time: 下午9:03
 */
@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = UserException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResultVO handlerException(UserException e) {
        return ResultVoUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = UserNoLoginException.class)
    @ResponseStatus(code = HttpStatus.FOUND)
    public String toLogin(UserNoLoginException e) {
        return "redirect:/user/login?url=" + e.getUrl();
    }
}
