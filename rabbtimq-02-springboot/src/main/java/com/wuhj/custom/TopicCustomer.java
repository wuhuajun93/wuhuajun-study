package com.wuhj.custom;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wuhj
 * @date 2020/12/23 15:31
 */
@Component
public class TopicCustomer {
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(type = "topic", name = "topics"),
                    key = {"user.save", "user.*"}
            )
    })
    public void receive1(String message){
        System.out.println("message 1 = " + message);
    }
    
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(type = "topic", name = "topics"),
                    key = {"order.#", "produce.#", "user.*"}
            )
    })
    public void receive2(String message){
        System.out.println("message 2 = " + message);
    }
}
