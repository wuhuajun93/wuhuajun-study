package com.wuhj.workqueue;

import com.rabbitmq.client.*;
import com.wuhj.util.RabbitMQUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author wuhj
 * @date 2020/12/21 16:09
 */
public class CustomerTwo {
    
    public static void main(String[] args) throws IOException {
    
    
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);
        channel.queueDeclare("work", true, false, false, null);
        //消费消息
        //参数1：队列名称
        //参数2：自动确认 true 开启自动确认 false 关闭自动确认
        //参数3：消费时回调接口
        channel.basicConsume("work", false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者-2:" + new String(body));
                //手动确认  参数1：手动确认消息标识  参数2：false 每次确认一个
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
