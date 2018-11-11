package com.cheng.sell.controller;

import com.cheng.sell.dto.OrderDTO;
import com.cheng.sell.enums.ResultEnum;
import com.cheng.sell.exception.SellException;
import com.cheng.sell.service.OrderService;
import com.cheng.sell.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.rest.type.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 支付
 * @author cheng
 * Date: 2018-07-09
 * Time: 上午8:04
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String,Object> map) {

        //1. 查询订单
        OrderDTO orderDTO = orderService.findById(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2. 发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("returnUrl", returnUrl);
        return new ModelAndView("pay/create",map);
    }

    /**
     * 微信异步通知
     * @param notifyData
     * @return
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);

        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }

}
