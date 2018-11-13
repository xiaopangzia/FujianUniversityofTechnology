package com.cheng.schoolsell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-26
 * Time: 下午2:59
 */
@Data
public class BusinessPwdForm {

    /**
     * 旧密码
     */
    @NotEmpty(message = "旧密码不能为空")
    private String businessPwd;

    /**
     * 新密码
     */
    @NotEmpty(message = "新密码不能为空")
    private String newBusinessPwd;

    /**
     * 重复新密码
     */
    @NotEmpty(message = "确认密码不能为空")
    private String verifyNewBusinessPwd;
}
