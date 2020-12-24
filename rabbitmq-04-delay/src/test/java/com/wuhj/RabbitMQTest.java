package com.wuhj;

import com.rabbitmq.client.*;
import com.wuhj.provider.Provider1;
import com.wuhj.util.RabbitMQUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author wuhj
 * @date 2020/12/24 11:19
 */
public class RabbitMQTest {
    
    private final static String DXL_EXCHANGE = "dlx.exchange";
    private final static String DLX_ROUTING_KEY = "dlx.routing.key";
    private final static String QUEUE = "worker-queue";
    @Test
    public void test() throws IOException {
        Provider1 provider1 = new Provider1();
        System.out.println("开始消费");
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(DXL_EXCHANGE, BuiltinExchangeType.DIRECT, true);
        channel.queueDeclare(QUEUE, true, false, false, null);
        channel.queueBind(QUEUE, DXL_EXCHANGE, DLX_ROUTING_KEY);
        channel.basicConsume(QUEUE, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("Consumer received message: " + new String(body));
            }
        });
        System.out.println("消费完成");
    
        try {
            TimeUnit.SECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
