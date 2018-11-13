package com.cheng.schoolsell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-14
 * Time: 下午2:25
 */
@Data
public class BusinessForm {

    /**
     * 登录手机
     */
    @NotEmpty(message = "手机号不能为空")
    private String businessPhone;

    /**
     * 商家密码
     */
    @NotEmpty(message = "密码不能为空")
    private String businessPwd;
}
