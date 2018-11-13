package com.cheng.schoolsell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-06
 * Time: 下午9:15
 */
@Data
public class AdminForm {

    /**
     * 管理员名
     */
    @NotEmpty(message = "管理员名不能为空")
    private String adminUser;

    /**
     * 管理员密码
     */
    @NotEmpty(message = "密码不能为空")
    private String adminPwd;
}
