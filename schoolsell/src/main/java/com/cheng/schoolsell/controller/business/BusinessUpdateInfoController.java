package com.cheng.schoolsell.controller.business;

import com.cheng.schoolsell.entity.BusinessInfo;
import com.cheng.schoolsell.enums.BusinessResultEnum;
import com.cheng.schoolsell.exception.BusinessException;
import com.cheng.schoolsell.form.BusinessPwdForm;
import com.cheng.schoolsell.service.SellerService;
import com.cheng.schoolsell.utils.CookieUtil;
import com.cheng.schoolsell.utils.ResultBusinessMapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-25
 * Time: 上午10:34
 */
@Controller
@Slf4j
@RequestMapping("/business/updateInfo")
@Api(tags = "商户端修改信息操作")
public class BusinessUpdateInfoController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/{num}")
    @ApiOperation(value = "跳转到商户修改信息页")
    public ModelAndView updateBusinessInfo(@PathVariable("num") Integer num,
                                           Map<String, Object> map) {
        if (num<1||num>3) {
            return new ModelAndView("redirect:http://127.0.0.1:8080/sell/business/index");
        }
        map.put("num", num);
        return new ModelAndView("business/updateBusinessInfo",map);
    }

    @PostMapping("/updateName")
    @ApiOperation(value = "修改商户名")
    public ModelAndView updateBusinessName(@RequestParam("businessName") String businessName,
                                           Map<String, Object> map,
                                           HttpServletRequest request) {

        if (StringUtils.isEmpty(businessName)) {
            throw new BusinessException(BusinessResultEnum.BUSINESS_IS_EXIST);
        }

        String id = CookieUtil.getSessionValue(request, "business");
        BusinessInfo businessInfo = sellerService.findById(id).get();
        businessInfo.setBusinessName(businessName);
        sellerService.save(businessInfo);
        return new ModelAndView("business/common/success",
                ResultBusinessMapUtil.resultMap(BusinessResultEnum.BUSINESS_UPDATE_NAME_SUCCESS));

    }

    @PostMapping("/updatePhone")
    @ApiOperation(value = "修改商户电话")
    public ModelAndView updateBusinessPhone(@RequestParam("businessPhone") String businessPhone,
                                            @RequestParam("oldBusinessPhone") String oldBusinessPhone,
                                            HttpServletRequest request) {

        //todo 后期添加阿里云短信服务，验证手机号

        if (StringUtils.isEmpty(businessPhone)||StringUtils.isEmpty(oldBusinessPhone)) {
            log.error("修改商户手机号：手机号为空,businessPhone={}", businessPhone);
            throw new BusinessException(BusinessResultEnum.BUSINESS_PHONE_EXIST);
        }

        String id = CookieUtil.getSessionValue(request, "business");
        BusinessInfo businessInfo = sellerService.findById(id).get();

        if (!businessInfo.getBusinessPhone().equals(oldBusinessPhone)) {
            log.error("修改密码：旧手机号码不一致 oldBusinessPhone = {}",oldBusinessPhone);
            throw new BusinessException(BusinessResultEnum.BUSINESS_OLD_PHONE_ERROR);
        }

        businessInfo.setBusinessPhone(businessPhone);
        sellerService.save(businessInfo);

        return new ModelAndView("business/common/success",
                ResultBusinessMapUtil.resultMap(BusinessResultEnum.BUSINESS_UPDATE_PHONE_SUCCESS));
    }

    @PostMapping("/updatePwd")
    @ApiOperation(value = "修改商户密码")
    public ModelAndView updateBusinessPwd(@Valid BusinessPwdForm businessPwdForm,
                                          BindingResult bindingResult,
                                          HttpServletRequest request) {

        if (bindingResult.hasFieldErrors()) {
            log.error("商户修改密码：密码为空 msg={}",bindingResult.getFieldError().getDefaultMessage());
            throw new BusinessException(BusinessResultEnum.BUSINESS_PWD_EXIST);
        }

        String id = CookieUtil.getSessionValue(request, "business");
        BusinessInfo businessInfo = sellerService.findById(id).get();

        if (!businessInfo.getBusinessPwd().equals(businessPwdForm.getBusinessPwd())) {
            log.error("商户修改密码：旧密码不正确businessPwd={}", businessPwdForm.getBusinessPwd());
            throw new BusinessException(BusinessResultEnum.BUSINESS_PWD_ERROR);
        }

        if (!businessPwdForm.getNewBusinessPwd().equals(businessPwdForm.getVerifyNewBusinessPwd())) {
            log.error("商户修改密码：两次输入的新密码不一致newPwd={},verifyPwd={}",
                    businessPwdForm.getNewBusinessPwd(),
                    businessPwdForm.getVerifyNewBusinessPwd());
            throw new BusinessException(BusinessResultEnum.BUSINESS_TWO_PWD_DIFFERENT);
        }

        businessInfo.setBusinessPwd(businessPwdForm.getNewBusinessPwd());
        sellerService.save(businessInfo);

        return new ModelAndView("business/common/success",
                ResultBusinessMapUtil.resultMap(BusinessResultEnum.BUSINESS_UPDATE_PWD_SUCCESS));

    }

}
