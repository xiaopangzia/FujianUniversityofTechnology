package com.zhiyou.model;

import java.util.List;
/**
 * 天气情况的详细信息
 * @author jack
 *
 */
public class Weather {

    private String city;
    private String cityid;
    private String citycode;
    private String date;
    private String week;
    private List<Hourly> hourly;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public List<Hourly> getHourly() {
        return hourly;
    }

    public void setHourly(List<Hourly> hourly) {
        this.hourly = hourly;
    }

    @Override
    public String toString() {
        return "Weather [city=" + city + ", cityid=" + cityid + ", citycode=" + citycode + ", date=" + date + ", week="
                + week + ", hourly=" + hourly + "]";
    }
}

