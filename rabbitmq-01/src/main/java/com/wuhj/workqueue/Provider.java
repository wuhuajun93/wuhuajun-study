package com.wuhj.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wuhj.util.RabbitMQUtil;

import java.io.IOException;

/**
 * @author wuhj
 * @date 2020/12/21 15:59
 */
public class Provider {
    
    public static void main(String[] args) throws IOException {
        //获取链接对象
        Connection connection = RabbitMQUtil.getConnection();
        //创建通道对象
        Channel channel = connection.createChannel();
        channel.confirmSelect();
        //通过通道声明队列
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 1; i <= 20; i++) {
            //生产消息
            channel.basicPublish("", "work", null, (i + "hello work queue").getBytes());
        }
        
        //关闭资源
        RabbitMQUtil.closeConnectionAndChannel(channel, connection);
    }
}
