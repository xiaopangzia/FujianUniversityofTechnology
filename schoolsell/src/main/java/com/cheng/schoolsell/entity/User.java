package com.cheng.schoolsell.entity;

import com.cheng.schoolsell.constant.UserConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 用户表
 * user: BinCher
 * Date: 2018-08-04
 * Time: 上午09：31
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  private String userId;

  /**
   * 用户名
   */
  private String username;

  /**
   * 密码
   */
  private String password;

  /**
   * 手机 唯一
   */
  private String phone;

  /**
   * 地址
   */
  private String address;

  /**
   * 用户头像
   */
  private String userImg = UserConstant.USERIMG;

}
