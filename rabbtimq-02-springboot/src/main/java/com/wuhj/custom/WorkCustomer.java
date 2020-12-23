package com.wuhj.custom;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wuhj
 * @date 2020/12/23 15:01
 *
 * 公平消费
 */
@Component
public class WorkCustomer {
    
    //一个消费者
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive1(String message){
        System.out.println("work message1 :" + message);
    }
    
    //一个消费者
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive2(String message){
        System.out.println("work message2 :" + message);
    }
}
