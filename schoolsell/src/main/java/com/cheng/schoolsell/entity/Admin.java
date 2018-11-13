package com.cheng.schoolsell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 管理员表
 * user: BinCher
 * Date: 2018-08-04
 * Time: 上午09：31
 */
@Entity
@Data
@DynamicUpdate
public class Admin {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer adminId;

  /**
   * 管理员名
   */
  private String adminUser;

  /**
   * 管理员密码
   */
  private String adminPwd;

  /**
   * 登陆后生成的token
   */
  private String token;

  /**
   * 上次登录时间
   */
  private Date updateTime;

}
