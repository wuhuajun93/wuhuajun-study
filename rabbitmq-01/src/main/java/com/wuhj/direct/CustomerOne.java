package com.wuhj.direct;

import com.rabbitmq.client.*;
import com.wuhj.util.RabbitMQUtil;

import java.io.IOException;

/**
 * @author wuhj
 * @date 2020/12/21 17:07
 */
public class CustomerOne {
    
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "logs_direct";
        
        //通过通道声明交换机以及交换的类型
        channel.exchangeDeclare(exchangeName, "direct");
        //创建一个临时队列
        String queue = channel.queueDeclare().getQueue();
        //基于route key绑定队列和交换机
        channel.queueBind(queue, exchangeName, "error");
        
        //获取消费的信息
        channel.basicConsume(queue, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1：" + new String(body));
            }
        });
    }
}
