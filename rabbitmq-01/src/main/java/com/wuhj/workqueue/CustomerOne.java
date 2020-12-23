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
        //通道绑定对应消息队列
        //参数1：队列名称， 如果队列不存在则自动创建
        //参数2：用来定义队列特性是否需要持久化 true 持久化  false 不持久化
        //参数3：exclusive 是否独占队列  true 独占队列  false 不独占
        //参数4：autoDelete 是否再消费完成后自动删除队列 true 自动删除 false 不自动删除
        //参数5：额外附加参数
        channel.queueDeclare("work", true, false, false, null);
        //消费消息
        //参数1：队列名称
        //参数2：自动确认 true 开启自动确认 false 关闭自动确认
        //参数3：消费时回调接口
        channel.basicConsume("work", false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-1:" + new String(body));
                //手动确认
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
        
    }
}
