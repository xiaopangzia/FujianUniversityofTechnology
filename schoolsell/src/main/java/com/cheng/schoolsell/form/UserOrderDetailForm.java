package com.cheng.schoolsell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-01
 * Time: 下午8:46
 */
@Data
public class UserOrderDetailForm {

    @NotEmpty(message = "商品不能为空")
    private String productId;

    @NotNull(message = "数量不能为空")
    private Integer quantity;
}
