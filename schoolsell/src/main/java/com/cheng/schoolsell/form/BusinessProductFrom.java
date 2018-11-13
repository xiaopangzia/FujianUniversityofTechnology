package com.cheng.schoolsell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-28
 * Time: 下午5:57
 */
@Data
public class BusinessProductFrom {

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品名
     */
    @NotEmpty(message = "商品名不能为空")
    private String productName;

    /**
     * 分类ID
     */
    @NotEmpty(message = "分类不能为空")
    private String categoryId;

    /**
     * 商品价格
     */
    @NotNull(message = "商品价格不能为空")
    private BigDecimal productPrice;

    /**
     * 商品库存
     */
    private Integer productStock;

    /**
     * 商品状态
     */
    private Integer productStatus = 0;

    /**
     * 商品图片
     */
    private String productImg;

}
