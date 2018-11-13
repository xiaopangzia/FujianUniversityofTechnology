package com.cheng.schoolsell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-06
 * Time: 上午10:49
 */
@Data
public class UserOrderUpdateForm {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "电话不能为空")
    private String phone;

    @NotEmpty(message = "地址不能为空")
    private String address;

    private String orderMessage;
}
