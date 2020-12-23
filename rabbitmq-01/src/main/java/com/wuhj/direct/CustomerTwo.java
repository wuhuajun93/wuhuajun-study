package com.wuhj.direct;

import com.rabbitmq.client.*;
import com.wuhj.util.RabbitMQUtil;

import java.io.IOException;

/**
 * @author wuhj
 * @date 2020/12/21 17:12
 */
public class CustomerTwo {
    
    public static void main(String[] args) throws IOException {
    
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "logs_direct";
        channel.exchangeDeclare(exchangeName, "direct");
    
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, exchangeName, "info");
        channel.queueBind(queue, exchangeName, "error");
        channel.queueBind(queue, exchangeName, "warning");
        
        channel.basicConsume(queue, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2：" + new String(body));
            }
        });
        
    }
}
