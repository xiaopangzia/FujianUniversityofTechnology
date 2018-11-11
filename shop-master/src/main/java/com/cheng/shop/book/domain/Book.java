package com.cheng.shop.book.domain;


import com.cheng.shop.category.domain.Category;

public class Book {

  private String bid;//主键
  private String bname;//图书名
  private int bnum;//数量
  private String author;//作者
  private double price;//定价
  private double currPrice;//当前价
  private double discount;//折扣
  private String press;//出版社
  private String publishtime;//出版时间
  private long edition;//版次
  private long pageNum;//页数
  private long wordNum;//字数
  private String printtime;//刷新时间
  private long booksize;//开本
  private String paper;//纸质
  private Category category;//所属分类
  private String image_w;//大图路径
  private String image_b;//小图路径


  public String getBid() {
    return bid;
  }

  public void setBid(String bid) {
    this.bid = bid;
  }

  public String getBname() {
    return bname;
  }

  public int getBnum() {
    return bnum;
  }

  public void setBnum(int bnum) {
    this.bnum = bnum;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public void setBname(String bname) {
    this.bname = bname;
  }


  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public double getCurrPrice() {
    return currPrice;
  }

  public void setCurrPrice(double currPrice) {
    this.currPrice = currPrice;
  }


  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }


  public String getPress() {
    return press;
  }

  public void setPress(String press) {
    this.press = press;
  }


  public String getPublishtime() {
    return publishtime;
  }

  public void setPublishtime(String publishtime) {
    this.publishtime = publishtime;
  }


  public long getEdition() {
    return edition;
  }

  public void setEdition(long edition) {
    this.edition = edition;
  }


  public long getPageNum() {
    return pageNum;
  }

  public void setPageNum(long pageNum) {
    this.pageNum = pageNum;
  }


  public long getWordNum() {
    return wordNum;
  }

  public void setWordNum(long wordNum) {
    this.wordNum = wordNum;
  }


  public String getPrinttime() {
    return printtime;
  }

  public void setPrinttime(String printtime) {
    this.printtime = printtime;
  }


  public long getBooksize() {
    return booksize;
  }

  public void setBooksize(long booksize) {
    this.booksize = booksize;
  }


  public String getPaper() {
    return paper;
  }

  public void setPaper(String paper) {
    this.paper = paper;
  }

  public String getImage_w() {
    return image_w;
  }

  public void setImage_w(String image_w) {
    this.image_w = image_w;
  }

  public String getImage_b() {
    return image_b;
  }

  public void setImage_b(String image_b) {
    this.image_b = image_b;
  }
}
