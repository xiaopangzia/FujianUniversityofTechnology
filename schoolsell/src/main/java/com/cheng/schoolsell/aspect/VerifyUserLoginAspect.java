package com.cheng.schoolsell.aspect;

import com.cheng.schoolsell.entity.User;
import com.cheng.schoolsell.exception.UserNoLoginException;
import com.cheng.schoolsell.service.UserService;
import com.cheng.schoolsell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-06
 * Time: 上午6:21
 * 切面
 */
@Aspect
@Component
@Slf4j
public class VerifyUserLoginAspect {

    @Autowired
    private UserService userService;

    @Pointcut("execution(public * com.cheng.schoolsell.controller.user.User*.*(..))" +
            "&&!execution(public * com.cheng.schoolsell.controller.user.UserLoginController.*(..))" +
            "&&!execution(public * com.cheng.schoolsell.controller.user.UserCommonController.*(..))")
    public void doVerifyUser() {
    }

    @Before("doVerifyUser()")
    public void verifyLogin() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        String url = request.getRequestURI();

        Cookie cookie = CookieUtil.get(request, "user");

        if (cookie == null || "".equals(cookie.getValue())) {
            log.error("verifyLogin() before:cookie没有值");
            throw new UserNoLoginException(url);
        }

        String key = cookie.getValue();

        String id = String.valueOf(request.getSession().getAttribute(key));

        Optional<User> user = userService.findById(id);

        if (!user.isPresent()) {
            log.error("verifyLogin() before: session 用户未找到");
            throw new UserNoLoginException(url);
        }

        /**
         * 每次登陆延长cookie和session的过期时间12小时
         */
        request.getSession().setMaxInactiveInterval(60 * 60 * 12);
        CookieUtil.set(response, "user", cookie.getValue(), 60 * 60 * 12);

    }

    @AfterReturning("doVerifyUser()")
    public void returnLogin() {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Cookie cookie = CookieUtil.get(request, "user");

        String key = cookie.getValue();
        String id = String.valueOf(request.getSession().getAttribute(key));
        Optional<User> user = userService.findById(id);
        request.setAttribute("id", user.get().getUserId());
        request.setAttribute("name", user.get().getUsername());
        request.setAttribute("img", user.get().getUserImg());

    }

}
