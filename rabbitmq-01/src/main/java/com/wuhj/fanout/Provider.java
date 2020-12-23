package com.wuhj.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wuhj.util.RabbitMQUtil;

import java.io.IOException;

/**
 * @author wuhj
 * @date 2020/12/21 16:42
 */
public class Provider {
    
    public static void main(String[] args) throws IOException {
    
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        
        channel.exchangeDeclare("logs", "fanout");
        
        channel.basicPublish("logs", "", null, "fanout type message".getBytes());
        
        RabbitMQUtil.closeConnectionAndChannel(channel, connection);
    }
}
