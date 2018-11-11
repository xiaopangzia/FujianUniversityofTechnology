package com.zhiyou.model;

public class City {

    // 城市id
    private String cityid;
    // 城市编码
    private String citycode;
    // 上级城市编码
    private String parentid;
    // 城市名称
    private String city;

    public City() {

    }

    public City(String cityid, String citycode, String parentid, String city) {
        super();
        this.cityid = cityid;
        this.citycode = citycode;
        this.parentid = parentid;
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

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "City [cityid=" + cityid + ", citycode=" + citycode + ", parentid=" + parentid + ", city=" + city + "]";
    }
}
