package com.cheng.schoolsell.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-09-29
 * Time: 上午9:51
 */
@Data
public class BusinessProductInfoVO {

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 所属分类id
     */
    private String categoryId;

    /**
     * 商品名
     */
    private String categoryName;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 商品图片
     */
    private String productImg;

    /**
     * 商品库存
     */
    private Integer productStock;

    /**
     * 商品状态
     * 0 下架 默认
     * 1 上架
     */
    private Integer productStatus;

    /**
     * 商品状态名
     */
    private String statusName;
}
