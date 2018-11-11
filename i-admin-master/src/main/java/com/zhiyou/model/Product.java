package com.zhiyou.model;

//产品类
public class Product {

    //产品id
    private String id;
    //产品图片
    private String image;
    //产品名
    private String name;
    //产品价格
    private Double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product(String id, String image, String name, Double price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }
}
