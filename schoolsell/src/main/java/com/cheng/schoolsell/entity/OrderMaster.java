package com.cheng.schoolsell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 订单表
 * user: BinCher
 * Date: 2018-08-04
 * Time: 上午09：33
 */
@Entity
@Data
@DynamicInsert
public class OrderMaster {

  @Id
  private String orderId;

  /**
   * 所属商铺id
   */
  private String shopId;

  /**
   * 商铺名
   */
  private String orderName;

  /**
   * 所属用户
   */
  private String userId;

  /**
   * 用户名
   */
  private String username;

  /**
   * 用户电话
   */
  private String phone;

  /**
   * 用户地址
   */
  private String address;

  /**
   * 订单总价
   */
  private BigDecimal orderAmount;

  /**
   * 订单状态
   * 0 未支付
   * 1 已支付
   * 2 已取消
   */
  private Integer orderStatus;

  /**
   * 用户备注
   */
  private String orderMessage;

  /**
   * 创建时间
   */
  private Date createTime;

}
