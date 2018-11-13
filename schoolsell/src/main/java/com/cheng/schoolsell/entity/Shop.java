package com.cheng.schoolsell.entity;


import com.cheng.schoolsell.constant.ShopConstant;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 商铺表
 * user: BinCher
 * Date: 2018-08-04
 * Time: 上午09：33
 */
@Entity
@Data
public class Shop {

  @Id
  private String shopId;

  /**
   * 店铺名
   */
  private String shopName;

  /**
   * 所属商家id
   */
  private String businessId;

  /**
   * 区域划分id
   */
  private String regionId;

  /**
   * 商店logo
   */
  private String shopLogo = ShopConstant.SHOPIMG;

  /**
   * 商店描述
   */
  private String shopDescribe;

  /**
   * 商铺地址
   */
  private String shopAddr;

  /**
   * 商店电话
   */
  private String shopPhone;

  /**
   * 商铺状态
   * 0 休息中 默认
   * 1 营业中
   */
  private Integer shopStatus = 0;

  /**
   * 营业时间
   */
  private String shopHour;






}
