package com.wuhj.topic;

import com.rabbitmq.client.*;
import com.wuhj.util.RabbitMQUtil;

import java.io.IOException;

/**
 * @author wuhj
 * @date 2020/12/21 17:33
 */
public class CustomerOne {
    public static void main(String[] args) throws IOException {
    
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        
        channel.exchangeDeclare("topics", "topic");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, "topics", "user.#");
        
        channel.basicConsume(queue, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1：" + new String(body));
            }
        });
    }
}
