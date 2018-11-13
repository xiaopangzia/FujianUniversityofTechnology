package com.cheng.schoolsell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-01
 * Time: 下午8:34
 */
@Data
public class UserOrderMasterForm {

    @NotEmpty(message = "id不能为空")
    private String userId;

    @NotEmpty(message = "名字不能为空")
    private String name;

    @NotEmpty(message = "电话不能为空")
    private String phone;

    @NotEmpty(message = "商铺不能为空")
    private String shopId;

    @NotEmpty(message = "订单不能为空")
    private List<UserOrderDetailForm> detailFormList;

    private String orderMessage;

}
