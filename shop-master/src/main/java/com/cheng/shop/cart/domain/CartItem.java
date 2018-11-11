package com.cheng.shop.cart.domain;


import com.cheng.shop.book.domain.Book;
import com.cheng.shop.user.domain.User;

import java.math.BigDecimal;

public class CartItem {

    private String cartItemId;//主键
    private int quantity;//数量
    private Book book;//条目对应图书
    private User user;//所属用户

    //添加小计方法
    public double getSubtotal() {

        /**
         * 使用BigDecimal不会误差
         * 要求必须使用String类型构造器
         */
        BigDecimal bigDecimal = new BigDecimal(book.getCurrPrice() + "");
        BigDecimal bigDecimal1 = new BigDecimal(quantity + "");
        //bigDecimal*bigDecimal1
        BigDecimal bigDecimal2 = bigDecimal.multiply(bigDecimal1);
        return bigDecimal2.doubleValue();
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
