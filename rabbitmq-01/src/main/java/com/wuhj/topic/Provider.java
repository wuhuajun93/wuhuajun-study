package com.wuhj.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wuhj.util.RabbitMQUtil;

import java.io.IOException;

/**
 * @author wuhj
 * @date 2020/12/21 17:24
 */
public class Provider {
    
    public static void main(String[] args) throws IOException {
    
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        
        channel.exchangeDeclare("topics", "topic");
        String routeKey = "user.save.findAll";
        channel.basicPublish("topics", routeKey, null, ("这里是topic动态路由模型，routeKey: ["+ routeKey +"]").getBytes());
        
        RabbitMQUtil.closeConnectionAndChannel(channel, connection);
    }
}
