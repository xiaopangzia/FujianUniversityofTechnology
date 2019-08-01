package com.cheng.order.message;

import com.cheng.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Cheng
 * @DATE: 2019-07-20
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    /*@StreamListener(StreamClient.OUTPUT)
    public void process(Object message) {
        log.info("StreamReceiver:{}", message);
    }*/

    @StreamListener(value = StreamClient.OUTPUT)
    @SendTo(StreamClient.INPUT)
    public String process(OrderDTO message) {
        log.info("StreamReceiver:{}", message);
        return "Receiver.";
    }

}
