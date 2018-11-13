package com.cheng.schoolsell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.StringJoiner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-10-10
 * Time: 上午9:22
 */
@Data
public class BusinessShopForm {

    @NotEmpty(message = "找不到商铺")
    private String shopId;

    @NotEmpty(message = "商铺名不能为空")
    private String shopName;

    @NotEmpty(message = "所属分类不能为空")
    private String regionId;

    @NotEmpty(message = "商铺电话不能为空")
    private String shopPhone;

    @NotEmpty(message = "商铺运营起始时间不能为空")
    private String startTime;

    @NotEmpty(message = "商铺运营结束时间不能为空")
    private String endTime;

    private String shopDescribe;

}
