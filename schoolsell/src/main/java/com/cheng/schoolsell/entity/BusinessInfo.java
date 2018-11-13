package com.cheng.schoolsell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 商家信息表
 * user: BinCher
 * Date: 2018-08-04
 * Time: 上午09：31
 */
@Entity
@Data
public class BusinessInfo {

  @Id
  private String businessId;

  /**
   * 商家名
   */
  private String businessName;

  /**
   * 商家手机，用户登录
   */
  private String businessPhone;

  /**
   * 商家密码
   */
  private String businessPwd;

  /**
   * 商家的店铺id
   */
  private String shopId;

}
