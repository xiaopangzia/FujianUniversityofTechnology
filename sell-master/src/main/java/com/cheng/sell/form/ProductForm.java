package com.cheng.sell.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author cheng
 * Date: 2018-07-11
 * Time: 上午9:35
 */
@Data
public class ProductForm {

    private String productId;

    /** 名称 */
    private String productName;

    /** 价格 */
    private BigDecimal productPrice;

    /** 库存 */
    private Integer productStock;

    /** 描述 */
    private String productDescription;

    /** 小图 */
    private String productIcon;

    /** 类目编号 */
    private Integer categoryType;

}
