package com.cheng.order.controller;

import com.cheng.order.dto.OrderDTO;
import com.cheng.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Cheng
 * @DATE: 2019-07-20
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    /*@GetMapping("/sendMessage")
    public void process() {
        streamClient.output().send(MessageBuilder
                .withPayload("now " + LocalDateTime.now()).build());
    }*/

    @GetMapping("/sendMessage")
    public void process() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("12321");
        streamClient.output().send(MessageBuilder
                .withPayload(orderDTO).build());
    }

}
