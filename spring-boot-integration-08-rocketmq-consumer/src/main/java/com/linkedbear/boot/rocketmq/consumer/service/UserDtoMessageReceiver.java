package com.linkedbear.boot.rocketmq.consumer.service;

import com.linkedbear.boot.rocketmq.consumer.dto.UserDTO;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "test-dto", consumerGroup = "dtoconsumer")
public class UserDtoMessageReceiver implements RocketMQListener<UserDTO> {
    
    @Override
    public void onMessage(UserDTO message) {
        System.out.println("接收到DTO消息");
        System.out.println(message.toString());
    }
}
