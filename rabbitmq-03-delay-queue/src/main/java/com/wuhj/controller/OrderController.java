package com.wuhj.controller;

/**
 * @author wuhj
 * @date 2020/12/23 18:08
 */

import com.wuhj.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger =  LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private AmqpTemplate rabbitTemplate;
    
    /**
     * 模拟提交订单
     * @author nxq
     * @return java.lang.Object
     */
    @GetMapping("")
    public Object submit(){
        String orderId = UUID.randomUUID().toString();
        logger.info("submit order {}", orderId);
        this.rabbitTemplate.convertAndSend(
                RabbitMQConfig.orderExchange, //发送至订单交换机
                RabbitMQConfig.routingKeyOrder, //订单定routingKey
                orderId //订单号   这里可以传对象 比如直接传订单对象
                , message -> {
                    // 如果配置了 params.put("x-message-ttl", 5 * 1000);
                    // 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
                    message.getMessageProperties().setExpiration(1000 * 10 + "");
                    return message;
                });
        
        return "{'orderId':'"+orderId+"'}";
    }
}