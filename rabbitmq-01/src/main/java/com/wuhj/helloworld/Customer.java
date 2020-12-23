package com.wuhj.helloworld;

import com.rabbitmq.client.*;
import com.wuhj.util.RabbitMQUtil;

import java.io.IOException;

/**
 * @author wuhj
 * @date 2020/12/21 14:57
 */
public class Customer {
    
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", true, false, false, null);
        //消费消息
        //参数1：消费那个队列的消息 队列名称
        //参数2：开始消息的自动确认机制
        //参数3：消费时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            @Override//最后一个参数：消息队列中取出的消息
            public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println("new String(body):" + new String(bytes));
            
            }
        });
        
    }
}
