package com.cheng.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-03
 * Time: 下午3:18
 */
@Data
public class OrderForm {

    /** 买家姓名 */
    @NotEmpty(message = "姓名必填")
    private String name;

    /** 买家电话 */
    @NotEmpty(message = "买家电话必填")
    private String phone;

    /** 买家地址 */
    @NotEmpty(message = "买家地址必填")
    private String address;

    /** 买家微信openid */
    @NotEmpty(message = "买家微信openid必填")
    private String openid;

    /** 购物车 */
    @NotEmpty(message = "购物车不能为空")
    private String items;


}
