package com.linkedbear.boot.rocketmq.consumer.service;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RocketMQMessageListener(topic = "test-sender", consumerGroup = "myconsumer")
public class MessageReceiver implements RocketMQListener<MessageExt> {
    
//    @Override
//    public void onMessage(String message) {
//        System.out.println(System.currentTimeMillis());
//        System.out.println("收到消息：" + message);
//        int i = 1 / 0;
//    }
    
    @Override
    public void onMessage(MessageExt ext) {
        int reconsumeTimes = ext.getReconsumeTimes();
        if (reconsumeTimes > 3) {
            // 将消息保存到数据库
            // 直接返回，代表消息已经消费成功
            return;
        }
        System.out.println(System.currentTimeMillis());
        String message = new String(ext.getBody(), StandardCharsets.UTF_8);
        System.out.println("收到消息：" + message);
        int i = 1 / 0;
    }
}
