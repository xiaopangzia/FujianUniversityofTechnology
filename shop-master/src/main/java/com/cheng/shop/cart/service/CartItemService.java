package com.cheng.shop.cart.service;

import cn.itcast.commons.CommonUtils;
import com.cheng.shop.cart.dao.CartItemDao;
import com.cheng.shop.cart.domain.CartItem;

import java.sql.SQLException;
import java.util.List;

public class CartItemService {
    private CartItemDao cartItemDao=new CartItemDao();

    //我的购物车
    public List<CartItem> myCart(String uid) {
        try {
            return cartItemDao.findByUser(uid);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //加载多个CartItem
    public List<CartItem> loadCartItem(String cartItemIds) {
        try {
            return cartItemDao.loadCartItems(cartItemIds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //修改购物车数量
    public CartItem updateQuantity(String cartItemId, int quantity) {
        try {
            cartItemDao.updateQuantity(cartItemId, quantity);
            return cartItemDao.findByCartItemId(cartItemId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //批量删除
    public void batchDelete(String  cartItemIds) {
        try {
            cartItemDao.batchDelete(cartItemIds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //添加条目
    public void addCartItem(CartItem cartItem) {
        // 1.使用uid和bid去数据库中查询这个条目是否存在
        try {
            CartItem _cartItem = cartItemDao.findByUidAndBid(
                    cartItem.getUser().getUid(),
                    cartItem.getBook().getBid());
            //如果原来没有这个条目，那么添加条目
            if (_cartItem == null) {
                cartItem.setCartItemId(CommonUtils.uuid());
                cartItemDao.addCartItem(cartItem);
            }else{
                //如果原来有这个条目，修改数量
                //使用原有数量和新条目数量之和，来做为新的数量
                int quantity = cartItem.getQuantity() + _cartItem.getQuantity();
                //修改这个老条目的数量
                cartItemDao.updateQuantity(_cartItem.getCartItemId(), quantity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
