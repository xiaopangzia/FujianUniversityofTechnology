package com.cheng.schoolsell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: BinCher
 * Date: 2018-08-23
 * Time: 下午12:35
 */
@Data
public class AdminShopForm {

    /**
     * 商铺名
     */
    @NotEmpty(message = "商铺名不能为空")
    private String ShopName;

    /**
     * 所属商家ID
     */
    @NotEmpty(message = "必须选择商家")
    private String businessId;

    /**
     * 区域分类ID
     */
    @NotEmpty(message = "必须选择区域")
    private String regionId;

    /**
     * 商铺地址
     */
    @NotEmpty(message = "商铺地址必填")
    private String shopAddr;

    /**
     * 商铺电话
     */
    @NotEmpty(message = "商铺电话不能为空")
    private String shopPhone;
}
