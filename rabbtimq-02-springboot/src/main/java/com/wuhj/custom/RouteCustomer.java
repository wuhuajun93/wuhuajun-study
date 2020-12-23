package com.wuhj.custom;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wuhj
 * @date 2020/12/23 15:24
 *
 * 路由模型
 */
@Component
public class RouteCustomer {
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "directs", type = "direct"),
                    key = {"info","error","warn"}
            )
    })
    public void receice1(String message){
        System.out.println("message 1 = " + message);
    }
    
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "directs", type = "direct"),
                    key = {"error"}
            )
    })
    public void receice2(String message){
        System.out.println("message 2 = " + message);
    }
    
}
