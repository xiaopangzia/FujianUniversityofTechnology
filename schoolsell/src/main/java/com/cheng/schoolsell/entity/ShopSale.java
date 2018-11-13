package com.cheng.schoolsell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 销量表
 * user: BinCher
 * Date: 2018-08-04
 * Time: 上午09：33
 */
@Entity
@Data
public class ShopSale {

  @Id
  private String saleId;

  /**
   * 所属店铺id
   */
  private String shopId;

  /**
   * 商品id
   */
  private String productId;

  /**
   * 商品名
   */
  private String productName;

  /**
   * 日销量
   */
  private Integer saleNum;

  /**
   * 日营业额
   */
  private BigDecimal turnover;

  /**
   * 所属日期
   */
  private LocalDate saleTime;

}
