package com.cheng.sell.service;

import com.cheng.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 支付服务
 * @author cheng
 * Date: 2018-07-09
 * Time: 上午8:07
 */
public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);

}
