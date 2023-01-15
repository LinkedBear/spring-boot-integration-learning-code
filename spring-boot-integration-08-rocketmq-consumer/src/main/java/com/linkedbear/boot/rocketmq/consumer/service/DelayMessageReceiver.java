package com.linkedbear.boot.rocketmq.consumer.service;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "test-delay", consumerGroup = "delayconsumer")
public class DelayMessageReceiver implements RocketMQListener<String> {
    
    @Override
    public void onMessage(String message) {
        System.out.println("接收到消息：" + message);
        System.out.println(System.currentTimeMillis());
    }
}
