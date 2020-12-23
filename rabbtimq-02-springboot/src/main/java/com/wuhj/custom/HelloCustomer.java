package com.wuhj.custom;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wuhj
 * @date 2020/12/21 17:54
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))
public class HelloCustomer {
    
    @RabbitHandler
    public void receive(String message){
        System.out.println("message = " + message);
    }
}
