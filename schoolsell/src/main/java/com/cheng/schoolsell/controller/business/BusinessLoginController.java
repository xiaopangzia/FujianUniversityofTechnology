package com.cheng.schoolsell.controller.business;

import com.cheng.schoolsell.entity.BusinessInfo;
import com.cheng.schoolsell.enums.BusinessResultEnum;
import com.cheng.schoolsell.exception.BusinessException;
import com.cheng.schoolsell.form.BusinessForm;
import com.cheng.schoolsell.service.SellerService;
import com.cheng.schoolsell.utils.CookieUtil;
import com.cheng.schoolsell.utils.KeyUtil;
import com.cheng.schoolsell.utils.ResultBusinessMapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-12
 * Time: 下午3:31
 */
@RequestMapping("/business")
@Controller
@Slf4j
@Api(tags = "商户端登录操作")
public class BusinessLoginController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/login")
    @ApiOperation(value = "跳转到商户登录页")
    public ModelAndView businessLogin(){
        return new ModelAndView("business/login");
    }

    @PostMapping("/login")
    @ApiOperation(value = "商户登录验证")
    public ModelAndView businessLogin(@Valid BusinessForm businessForm,
                                      BindingResult bindingResult,
                                      HttpServletResponse response,
                                      HttpServletRequest request,
                                      Map<String, Object> map) {
        if (bindingResult.hasFieldErrors()) {
            log.error("登录失败，msg={}",bindingResult.getFieldError().getDefaultMessage());
            throw new BusinessException(
                    bindingResult.getFieldError().getDefaultMessage(),"/sell/business/login");
        }

        Optional<BusinessInfo> businessInfo = sellerService.businessLoginByPhoneAndPwd(businessForm);
        if (!businessInfo.isPresent()) {
            log.error("商户登录，商户不存在");
            throw new BusinessException(BusinessResultEnum.BUSINESS_LOGIN_ERROR_BY_EXIST);
        }

        String id = businessInfo.get().getBusinessId();

        /**
         * 存到cookie的value里
         * 获取cookie的value，把这个当做session的key
         * 通过这个key 查询到商户的id
         */
        String uuid = KeyUtil.getUUID();

        CookieUtil.set(response,"business", uuid,60*60*24);
        request.getSession().setAttribute(uuid, id);
        request.getSession().setMaxInactiveInterval(60*60*24);

        return new ModelAndView("business/common/success",
                ResultBusinessMapUtil.resultMap(BusinessResultEnum.BUSINESS_LOGIN_SUCCESS));
    }

    @GetMapping("/logout")
    @ApiOperation(value = "商户退出")
    public ModelAndView businessLogout(HttpServletResponse response,
                                       HttpServletRequest request) {
        Cookie cookie = CookieUtil.get(request, "business");
        if (cookie == null) {
            return new ModelAndView("business/common/success",
                    ResultBusinessMapUtil.resultMap(BusinessResultEnum.BUSINESS_LOGOUT_SUCCESS));
        }
        String key = cookie.getValue();
        CookieUtil.set(response, "business", "", 0);
        request.getSession().removeAttribute(key);

        return new ModelAndView("business/common/success",
                ResultBusinessMapUtil.resultMap(BusinessResultEnum.BUSINESS_LOGOUT_SUCCESS));
    }


}
