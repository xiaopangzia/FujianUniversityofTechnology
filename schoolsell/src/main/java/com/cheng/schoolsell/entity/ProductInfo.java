package com.cheng.schoolsell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 商品信息表
 * user: BinCher
 * Date: 2018-08-04
 * Time: 上午09：33
 */
@Entity
@Data
public class ProductInfo {

  @Id
  private String productId;

  /**
   * 商品名
   */
  private String productName;

  /**
   * 所属分类id
   */
  private String categoryId;

  /**
   * 商品价格
   */
  private BigDecimal productPrice;

  /**
   * 所属店铺id
   */
  private String shopId;

  /**
   * 商品图片
   */
  private String productImg;

  /**
   * 商品库存
   */
  private Integer productStock;

  /**
   * 商品状态
   * 0 下架 默认
   * 1 上架
   */
  private Integer productStatus = 0;


}
