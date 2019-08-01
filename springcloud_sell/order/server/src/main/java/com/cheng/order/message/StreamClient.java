package com.cheng.order.message;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Cheng
 * @DATE: 2019-07-20
 */
public interface StreamClient {

    String INPUT = "input";

    String OUTPUT = "output";


    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    @Output(StreamClient.OUTPUT)
    MessageChannel output();


}
