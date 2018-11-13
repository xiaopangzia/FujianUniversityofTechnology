package com.cheng.schoolsell.aspect;

import com.cheng.schoolsell.entity.BusinessInfo;
import com.cheng.schoolsell.entity.Shop;
import com.cheng.schoolsell.enums.BusinessResultEnum;
import com.cheng.schoolsell.exception.BusinessException;
import com.cheng.schoolsell.service.SellerService;
import com.cheng.schoolsell.service.ShopService;
import com.cheng.schoolsell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-19
 * Time: 下午2:56
 */
@Aspect
@Component
@Slf4j
public class VerifyBusinessAspect {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ShopService shopService;

    @Pointcut("execution(public * com.cheng.schoolsell.controller.business.Business*.*(..))" +
            "&& !execution(public * com.cheng.schoolsell.controller.business.BusinessLoginController.*(..))")
    public void verifyBusiness() {}

    @Before("verifyBusiness()")
    public void verifyBusinessLogin() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Cookie cookie = CookieUtil.get(request, "business");

        if (cookie == null||"".equals(cookie.getValue())) {
            log.error("verifyBusiness() before:cookie没有值");
            throw new BusinessException(BusinessResultEnum.BUSINESS_LOGOUT);
        }

        String key = cookie.getValue();

        String id = String.valueOf(request.getSession().getAttribute(key));

        Optional<BusinessInfo> businessInfo = sellerService.findById(id);

        if (!businessInfo.isPresent()) {
            log.error("verifyBusiness() before: session 商户未找到");
            throw new BusinessException(BusinessResultEnum.BUSINESS_LOGOUT);
        }

    }

    @AfterReturning("verifyBusiness()")
    public void returnBusinessMsg() {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Cookie cookie = CookieUtil.get(request, "business");
        String key = cookie.getValue();
        String id = String.valueOf(request.getSession().getAttribute(key));
        Optional<BusinessInfo> businessInfo = sellerService.findById(id);

        if (!StringUtils.isEmpty(businessInfo.get().getShopId())) {
            Optional<Shop> shop = shopService.findById(businessInfo.get().getShopId());
            request.setAttribute("shopStatus",shop.get().getShopStatus());
            request.setAttribute("shopId",shop.get().getShopId());
        }
        request.setAttribute("business", businessInfo.get());
        request.setAttribute("time", LocalDateTime.now());
    }

}
