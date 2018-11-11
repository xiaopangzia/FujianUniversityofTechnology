package com.zhiyou.model;

import com.zhiyou.model.City;

import java.util.List;

/**
 * 封装城市数据请求响应结果
 *
 * @author LIJIKE
 *
 */
public class RCity {
    // 返回状态
    private String status;
    // 返回消息
    private String msg;
    // 返回的数据集
    private List<City> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<City> getResult() {
        return result;
    }

    public void setResult(List<City> result) {
        this.result = result;
    }
}
