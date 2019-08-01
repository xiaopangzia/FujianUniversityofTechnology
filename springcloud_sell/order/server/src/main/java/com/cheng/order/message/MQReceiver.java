package com.cheng.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Cheng
 * @DATE: 2019-07-20
 */
@Component
@Slf4j
public class MQReceiver {

    // 监听队列 @RabbitListener(queues = "myQueue")
    // 自动创建队列 @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    // 把队列绑定到exchange上
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message) {
        log.info(message);
    }

    /**
     * 数码供应商 接收消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myOrder"),
            key = "computer",
            exchange = @Exchange("computerOrder")
    ))
    public void processComputer(String message) {
        log.info("computer MQ = {}" ,message);
    }

    /**
     * 水果供应商 接收消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myOrder"),
            key = "fruit",
            exchange = @Exchange("fruitOrder")
    ))
    public void processFruit(String message) {
        log.info("fruit MQ = {}" ,message);
    }

}
