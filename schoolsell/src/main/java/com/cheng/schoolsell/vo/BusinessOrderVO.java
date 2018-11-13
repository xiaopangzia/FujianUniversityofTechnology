package com.cheng.schoolsell.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cheng
 * Date: 2018-11-10
 * Time: 下午3:59
 */
@Data
public class BusinessOrderVO {

    private String orderId;

    private String username;

    private String phone;

    private BigDecimal orderAmount;

    private String address;

    private Integer orderStatus;

    private String orderMessage;

    private Date createTime;

    private List<OrderDetailVO> orderDetailVOS;

}
