package com.cheng.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 卖家信息
 * @author cheng
 * Date: 2018-07-12
 * Time: 11.04
 */
@Data
@Entity
public class SellerInfo {

  @Id
  private String sellerId;

  private String username;

  private String password;

  private String openid;

}
