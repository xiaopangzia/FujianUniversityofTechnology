package com.cheng.schoolsell.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-05
 * Time: 下午9:45
 */
@Data
public class OrderDetailVO {

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private BigDecimal productAmount;
}
