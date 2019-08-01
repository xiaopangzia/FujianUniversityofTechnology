package com.cheng.order.message;

import com.cheng.order.ServerApplicationTests;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Cheng
 * @DATE: 2019-07-20
 */
@Component
public class MQSenderTest extends ServerApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void MQComputerTest() {
        amqpTemplate.convertAndSend("computerOrder", "computer", LocalDateTime.now());
    }

    @Test
    public void MQFruitTest() {
        amqpTemplate.convertAndSend("fruitOrder", "fruit", LocalDateTime.now());

    }

}
