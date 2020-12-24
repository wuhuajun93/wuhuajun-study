package com.wuhj.provider;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wuhj.util.RabbitMQUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhj
 * @date 2020/12/24 10:55
 */
public class Provider1 {
    
    public Provider1() throws IOException {
        connectAndDeclare();
    }
    
    private final static String EXCHANGE = "out.exchange";
    private final static String ROUTING_KEY = "out.routing.key";
    private final static String DXL_EXCHANGE = "dlx.exchange";
    private final static String DLX_ROUTING_KEY = "dlx.routing.key";
    private final static String QUEUE = "buffer-queue";
    
    public static void connectAndDeclare() throws IOException {
    
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        // 创建交换机"out.exchange"：生产者将消息通过"out.exchange"发送到"buffer-queue"。这里设置交换机类型为"direct"，当然也可以使用其他类型
        channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.DIRECT, true);
        // 创建死信交换机"dlx.exchange"："buffer-queue"中产生死信后，会通过此交换机发送出去。这里设置交换机类型为"direct"，当然也可以使用其他类型
        channel.exchangeDeclare(DXL_EXCHANGE, BuiltinExchangeType.DIRECT, true);
        // 创建缓冲队列"buffer-queue"，并为"buffer-queue"设置死信交换机参数：生产者发布的消息会先到达此"buffer-queue"，消息在"buffer-queue"中变成死信后，会通过死信交换机和死信路由键发送出去
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", DXL_EXCHANGE);       // 指定死信交换机参数（x-dead-letter-exchange）
        arguments.put("x-dead-letter-routing-key", DLX_ROUTING_KEY); // 指定死信路由键参数（x-dead-letter-routing-key）
        channel.queueDeclare(QUEUE, true, false, false, arguments);
        // 将缓冲队列"buffer-queue"绑定到交换机"out.exchange"，路由键为"out.routing.key"
        channel.queueBind(QUEUE, EXCHANGE, ROUTING_KEY);
        // 测试1：生产5条消息，分别设置消息的TTL为：1秒，2秒，3秒，4秒，5秒
        for (int i = 1; i <= 5; i++) {
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .expiration(String.valueOf(i * 1000L))
                    .build();
            String message = "" + i;
            channel.basicPublish(EXCHANGE, ROUTING_KEY, properties, message.getBytes());
        }
        
        //方式二：为队列设置ttl
        /*Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", DXL_EXCHANGE);       // 指定死信交换机参数（x-dead-letter-exchange）
        arguments.put("x-dead-letter-routing-key", DLX_ROUTING_KEY); // 指定死信路由键参数（x-dead-letter-routing-key）
        arguments.put("x-message-ttl",5000L);
        channel.queueDeclare(QUEUE, true, false, false, arguments);
        channel.queueBind(QUEUE, EXCHANGE, ROUTING_KEY);
        for (int i = 0; i < 5; i++) {
            channel.basicPublish(EXCHANGE, ROUTING_KEY, null, (i + "").getBytes());
        }*/
    
    }


}
