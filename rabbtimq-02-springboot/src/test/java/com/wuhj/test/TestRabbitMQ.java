package com.wuhj.test;

import com.wuhj.Rabbtimq02SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wuhj
 * @date 2020/12/21 17:47
 */
@SpringBootTest(classes = Rabbtimq02SpringbootApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQ {
    
    //注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    //hello world
    @Test
    public void testHello(){
        rabbitTemplate.convertAndSend("hello", "hello world");
    }
    
    @Test
    public void testWork() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "work模型" + i);
        }
    }
    
    //fanout 广播模型
    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("logs", "", "fanout的模型发送消息");
    }
    
    //route 路由模型
    @Test
    public void testRoute() {
        rabbitTemplate.convertAndSend("directs", "error", "发送info的key的路由信息");
    }
    
    //topic 订阅模型
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("topics","order","user.save 路由消息");
    }
}
