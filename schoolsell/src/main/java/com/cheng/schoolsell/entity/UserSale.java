package com.cheng.schoolsell.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class UserSale {

  @Id
  private String saleId;

  /**
   * 用户id
   */
  private String userId;

  /**
   * 用户名
   */
  private String username;

  /**
   * 每日用户消费
   */
  private BigDecimal turnover;

  /**
   * 用户每天订单
   */
  private Integer orderNum;

  /**
   * 日期
   */
  private LocalDate saleTime;


}
