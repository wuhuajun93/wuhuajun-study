package com.wuhj.custom;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wuhj
 * @date 2020/12/23 15:17
 */
@Component
public class FanoutCustomer {
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,  //历是队列
                    exchange = @Exchange(value = "logs", type = "fanout")  //绑定交换机
            )
    })
    public void receive1(String message) {
        System.out.println("message1 = " + message);
    }
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,  //历是队列
                    exchange = @Exchange(value = "logs", type = "fanout")  //绑定交换机
            )
    })
    public void receive2(String message) {
        System.out.println("message2 = " + message);
    }
}
