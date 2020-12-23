package com.wuhj.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.wuhj.util.RabbitMQUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * @author wuhj
 * @date 2020/12/21 14:42
 */
public class Provider {
    
    //生产消息
    @Test
    public void tsetSendMessage() throws IOException {
        
        Connection connection = RabbitMQUtil.getConnection();
        //获取连接通道
        Channel channel = connection.createChannel();
        
        //通道绑定对应消息队列
        //参数1：队列名称， 如果队列不存在则自动创建
        //参数2：用来定义队列特性是否需要持久化 true 持久化  false 不持久化
        //参数3：exclusive 是否独占队列  true 独占队列  false 不独占
        //参数4：autoDelete 是否再消费完成后自动删除队列 true 自动删除 false 不自动删除
        //参数5：额外附加参数
        channel.queueDeclare("hello", true, false, false, null);
        
        //发布消息
        //参数1：交换机名称 参数2：队列名称 参数3：传递消息额外设置 参数4：消息的具体内容
        channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbtimq".getBytes());
        //关闭通道
        RabbitMQUtil.closeConnectionAndChannel(channel, connection);
    }
    
}
