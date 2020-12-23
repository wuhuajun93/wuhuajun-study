package com.wuhj.fanout;

import com.rabbitmq.client.*;
import com.wuhj.util.RabbitMQUtil;

import java.io.IOException;

/**
 * @author wuhj
 * @date 2020/12/21 16:46
 */
public class CustomerTwo {
    
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        
        channel.exchangeDeclare("logs", "fanout");
        
        //临时队列
        String queueName = channel.queueDeclare().getQueue();
    
        //绑定交换机和队列
        channel.queueBind(queueName, "logs", "");
        //消费消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2：" + new String(body));
            }
        });
    }
}
