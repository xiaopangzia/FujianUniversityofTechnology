package com.cheng.schoolsell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 商品分类表
 * user: BinCher
 * Date: 2018-08-04
 * Time: 上午09：33
 */
@Entity
@Data
public class ProductCategory {

  @Id
  private String categoryId;

  /**
   * 分类名
   */
  private String categoryName;

  /**
   * 分类编号
   */
  private Integer categoryType;

  /**
   * 所属店铺id
   */
  private String shopId;


}
