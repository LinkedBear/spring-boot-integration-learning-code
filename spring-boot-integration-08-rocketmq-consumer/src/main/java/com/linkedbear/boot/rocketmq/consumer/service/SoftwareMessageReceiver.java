package com.linkedbear.boot.rocketmq.consumer.service;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "test-tags", consumerGroup = "tagsconsumer", selectorExpression = "software")
public class SoftwareMessageReceiver implements RocketMQListener<String> {
    
    @Override
    public void onMessage(String message) {
        System.out.println("收到software消息：" + message);
    }
}
