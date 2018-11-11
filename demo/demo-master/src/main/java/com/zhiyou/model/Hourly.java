package com.zhiyou.model;

/**
 * 每小时的天气情况
 *
 * @author jack
 *
 */
public class Hourly {

    // 时间
    private String time;
    // 天气
    private String weather;
    // 温度
    private String temp;
    // 图片
    private String img;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}

