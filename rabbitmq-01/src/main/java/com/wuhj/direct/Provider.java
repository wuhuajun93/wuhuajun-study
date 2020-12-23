package com.wuhj.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wuhj.util.RabbitMQUtil;

import java.io.IOException;

/**
 * @author wuhj
 * @date 2020/12/21 17:01
 */
public class Provider {
    
    public static void main(String[] args) throws IOException {
    
        Connection connection = RabbitMQUtil.getConnection();
    
        Channel channel = connection.createChannel();
        String exchangeName = "logs_direct";
        //通过通道声明交换机  参数1：交换机名称  参数2：type 路由模式
        channel.exchangeDeclare(exchangeName, "direct");
        //发送消息
        String routingKey = "tr";
        channel.basicPublish(exchangeName, routingKey, null, ("这是direct模型发布的基于route key: ["  + routingKey + "] 发送的消息").getBytes());
        
        RabbitMQUtil.closeConnectionAndChannel(channel, connection);
    }
}
