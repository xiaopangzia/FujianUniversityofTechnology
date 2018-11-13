package com.cheng.schoolsell.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-05
 * Time: 下午9:40
 */
@Data
public class OrderMasterVO {

    private String orderId;

    private String shopId;

    private String shopName;

    private String shopImg;

    private String username;

    private String phone;

    private String address;

    private Integer orderStatus;

    private String shopPhone;

    private BigDecimal orderAmount;

    private String orderMessage;

    private Date createTime;

    private List<OrderDetailVO> orderDetailVOs;
}
