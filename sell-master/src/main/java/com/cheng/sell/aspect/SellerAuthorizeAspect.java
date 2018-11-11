package com.cheng.sell.aspect;

import com.cheng.sell.constant.CookieConstant;
import com.cheng.sell.constant.RedisConstant;
import com.cheng.sell.exception.SellException;
import com.cheng.sell.exception.SellerAuthorizeException;
import com.cheng.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-12
 * Time: 下午4:46
 */
//@Aspect
//@Component
//@Slf4j
public class SellerAuthorizeAspect {

//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Pointcut("execution(public * com.cheng.sell.controller.Seller*.*(..))"+
//            "&& !execution(public * com.cheng.sell.controller.SellerUserController.*(..))")
//    public void verify(){}
//
//    @Before("verify()")
//    public void doVerify() {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        Cookie cookie = CookieUtil.get(request, CookieConstant.Token);
//        if (cookie == null) {
//            log.warn("[登录异常]Cookie中查不到token");
//            throw new SellerAuthorizeException();
//        }
//
//        //去redis查
//        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PRIFIX, cookie.getValue()));
//        if (StringUtils.isEmpty(tokenValue)) {
//            log.warn("[登录异常] Redis中查不到token");
//            throw new SellerAuthorizeException();
//        }
//    }


}
