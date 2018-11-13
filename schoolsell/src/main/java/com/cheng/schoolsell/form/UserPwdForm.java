package com.cheng.schoolsell.form;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-10-27
 * Time: 上午10:20
 */
@Data
public class UserPwdForm {

    @Size(min = 9,message = "旧密码不能小于9位")
    private String password;

    @Size(min = 9,message = "新密码不能小于9位")
    private String newPassword;

    @Size(min = 9,message = "确认密码不能小于9位")
    private String verifyNewPassword;

}
