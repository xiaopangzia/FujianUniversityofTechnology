package com.cheng.schoolsell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 区域划分表
 * user: BinCher
 * Date: 2018-08-04
 * Time: 上午09：33
 */
@Entity
@Data
public class RegionCategory implements Serializable {

  private static final long serialVersionUID = -5652702894141719182L;

  @Id
  private String regionId;

  /**
   * 区域划分名
   */
  private String regionName;

  /**
   * 区域划分编号
   */
  private Integer regionType;


}
