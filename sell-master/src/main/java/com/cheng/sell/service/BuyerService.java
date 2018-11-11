package com.cheng.sell.service;

import com.cheng.sell.dto.OrderDTO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 买家
 * @author cheng
 * Date: 2018-07-03
 * Time: 下午5:23
 */
public interface BuyerService {

    /**
     * 查询一个订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOrderOne(String openid,String orderId);

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid, String orderId);

}
