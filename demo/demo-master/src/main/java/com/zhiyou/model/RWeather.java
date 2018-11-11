package com.zhiyou.model;
/**
 * 天气数据的返回结果
 * @author jack
 *
 */
public class RWeather {

    // 返回状态
    private String status;
    // 返回消息
    private String msg;

    private Weather result;

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

    public Weather getResult() {
        return result;
    }

    public void setResult(Weather result) {
        this.result = result;
    }
}

