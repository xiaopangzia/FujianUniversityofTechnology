package com.cheng.shop.category.domain;


import java.util.List;

/**
 * 双向自身关联
 */
public class Category {

  private String cid;//主键
  private String cname;//分类名
  private Category parent;//父分类
  private String desc;//说明
  private List<Category> children;//子分类

  public List<Category> getChildren( ){
    return children;
  }

  public void setChildren(List<Category> children) {
    this.children = children;
  }

  public String getCid() {
    return cid;
  }

  public void setCid(String cid) {
    this.cid = cid;
  }


  public String getCname() {
    return cname;
  }

  public void setCname(String cname) {
    this.cname = cname;
  }


  public Category getParent() {
    return parent;
  }

  public void setParent(Category parent) {
    this.parent = parent;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

}
