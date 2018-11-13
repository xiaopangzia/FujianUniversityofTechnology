package com.cheng.schoolsell.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-10-31
 * Time: 下午3:18
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 8299677324620477514L;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private String productImg;

}
