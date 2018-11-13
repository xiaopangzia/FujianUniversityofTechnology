package com.cheng.schoolsell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-05
 * Time: 下午4:28
 */
@Data
public class UserDTO implements Serializable {


    private static final long serialVersionUID = -7983535073558021284L;

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
     * 修改密码时的新密码
     */
    private String newPassword;

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
    private String userImg;

    /**
     * 请求中附带的token
     */
    private String token;
}
