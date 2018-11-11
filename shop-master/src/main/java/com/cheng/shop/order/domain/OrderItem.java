package com.cheng.shop.order.domain;


import com.cheng.shop.book.domain.Book;
import com.cheng.shop.order.domain.Order;

public class OrderItem {

  private String orderItemId;//主键
  private int quantity;//数量
  private double subtotal;//小计
  private Book book;//书籍信息
  private Order order;//所属订单

  public String getOrderItemId() {
    return orderItemId;
  }

  public void setOrderItemId(String orderItemId) {
    this.orderItemId = orderItemId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(double subtotal) {
    this.subtotal = subtotal;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }
}
