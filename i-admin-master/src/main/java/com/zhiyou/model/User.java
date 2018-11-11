package com.zhiyou.model;

/**
 * 用户类
 */
public class User {

    //用户唯一标识
    private String id;
    //用户名
    private String username;
    //真实姓名
    private String name;
    //密码
    private String password;
    //电话
    private String mobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public User(String id, String username, String name, String mobile) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.mobile = mobile;
    }
}
