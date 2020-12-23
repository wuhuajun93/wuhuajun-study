package com.wuhj.util;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wuhj
 * @date 2020/12/21 15:14
 */
public class RabbitMQUtil {
    
    private static ConnectionFactory connectionFactory;
    
    static {
        //重量资源类
        //创建连接mq的连接工厂对象
        connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("127.0.0.1");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置连接那个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }
    
    //定义提供连接的方法
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
