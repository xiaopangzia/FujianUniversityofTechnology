package com.cheng.schoolsell.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * user: BinCher
 * Date: 2018-08-05
 * Time: 下午5:52
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 2407887000724073713L;

    private String userId;

    /**
     * 用户名
     */
    private String username;

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
     * token
     */
    private String token;

}
