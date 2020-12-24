package com.wuhj.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wuhj
 * @date 2020/12/24 10:48
 */
public class RabbitMQUtil {
    
    private static ConnectionFactory connectionFactory;
    
    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
    }
    
    //建立连接
    public static Connection getConnection(){
        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //关闭通道和连接的工具方法
    public static void closeConnectionAndChannel(Channel channel, Connection connection){
        try {
            if(channel != null){
                channel.close();
            }
            if(channel != null){
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
