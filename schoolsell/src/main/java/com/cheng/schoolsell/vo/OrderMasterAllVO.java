package com.cheng.schoolsell.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-07
 * Time: 下午4:31
 */
@Data
public class OrderMasterAllVO {

    private String orderId;

    private String shopId;

    private String orderName;

    private String shopName;

    private String shopImg;

    private Integer orderStatus;

    private BigDecimal orderAmount;

    private Date createTime;

}
