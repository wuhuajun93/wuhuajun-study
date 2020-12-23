package com.wuhj.workqueue;

import com.rabbitmq.client.*;
import com.wuhj.util.RabbitMQUtil;

import java.io.IOException;

/**
 * @author wuhj
 * @date 2020/12/21 16:04
 */
public class CustomerOne {
    
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("work", true, false, false, null);
        
        channel.basicConsume("work", false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1:" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
        
    }
}
