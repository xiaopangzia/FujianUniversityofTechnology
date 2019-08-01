package com.cheng.order.service;

import com.cheng.order.dto.OrderDTO;

/**
 * Created by 廖师兄
 * 2017-12-10 16:39
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单 卖家
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);

}
