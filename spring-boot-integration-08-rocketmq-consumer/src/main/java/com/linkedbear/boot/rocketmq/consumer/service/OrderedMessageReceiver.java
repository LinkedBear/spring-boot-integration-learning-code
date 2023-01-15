package com.linkedbear.boot.rocketmq.consumer.service;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "test-ordered", consumerGroup = "orderedconsumer", consumeMode = ConsumeMode.ORDERLY)
public class OrderedMessageReceiver implements RocketMQListener<String> {
    
    @Override
    public void onMessage(String message) {
        System.out.println(message);
    }
}
