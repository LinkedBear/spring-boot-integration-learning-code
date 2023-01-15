package com.linkedbear.boot.rocketmq.consumer.service;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "test-transactional", consumerGroup = "transactionalconsumer")
public class TransactionalReceiver implements RocketMQListener<String> {
    
    @Override
    public void onMessage(String message) {
        System.out.println("接收到事务消息！");
        System.out.println(message);
    }
}
