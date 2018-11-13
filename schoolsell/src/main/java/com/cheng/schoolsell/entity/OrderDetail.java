package com.cheng.schoolsell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 订单详细表
 * user: BinCher
 * Date: 2018-08-04
 * Time: 上午09：31
 */
@Entity
@Data
public class OrderDetail {

  @Id
  private String detailId;

  /**
   * 商铺id
   */
  private String shopId;

  /**
   * 所属订单id
   */
  private String orderId;

  /**
   * 商品id
   */
  private String productId;

  /**
   * 商品名
   */
  private String productName;

  /**
   * 商品价格
   */
  private BigDecimal productPrice;

  /**
   * 商品数量
   */
  private Integer productQuantity;

  /**
   * 商品图片
   */
  private String productImg;

  /**
   * 单件总价
   */
  private BigDecimal productAmount;


}
