package com.linkedbear.boot.rocketmq.producer.service;

import com.linkedbear.boot.rocketmq.producer.dto.UserDTO;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageSender {
    
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    
    @Autowired
    @Qualifier("dataUpdateRocketMQTemplate")
    private RocketMQTemplate dataUpdateRocketMQTemplate;
    
    public void sendMessage() {
        // 同步发送消息
        System.out.println(System.currentTimeMillis());
        rocketMQTemplate.convertAndSend("test-sender", "test message");
        System.out.println("发送同步消息成功");
        System.out.println(System.currentTimeMillis());
    }
    
    public void asyncSend() {
        // 异步发送消息
        rocketMQTemplate.asyncSend("test-sender", "test async message", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("异步消息发送成功");
                System.out.println(sendResult);
            }
    
            @Override
            public void onException(Throwable e) {
                System.out.println("消息发送失败，异常：" + e.getMessage());
                e.printStackTrace();
            }
        });
    }
    
    public void sendOneway() {
        System.out.println(System.currentTimeMillis());
        // 发送单向消息
        rocketMQTemplate.sendOneWay("test-sender", "test oneway message");
        System.out.println("发送单向消息成功");
        System.out.println(System.currentTimeMillis());
    }
    
    public void sendTagsMessage() {
        // 发送tags为"software"和"hardware"的消息
        rocketMQTemplate.convertAndSend("test-tags:software", "test software message");
        rocketMQTemplate.convertAndSend("test-tags:hardware", "test hardware message");
    }
    
    public void sendDto() {
        // 构造发送一个DTO模型
        UserDTO dto = new UserDTO();
        dto.setUserId(1);
        dto.setUserName("haha");
        dto.setSexName("male");
        rocketMQTemplate.convertAndSend("test-dto", dto);
    }
    
    public void sendDelayMessage() {
        // 同步发送延迟消息
        Message<String> message = MessageBuilder.withPayload("delay message").build();
        rocketMQTemplate.syncSend("test-delay", message, 3000, 2);
        System.out.println("同步延迟消息发送成功！时间：" + System.currentTimeMillis());
        
        // 异步发送延迟消息
        rocketMQTemplate.asyncSend("test-delay", message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("异步延迟消息发送成功！时间：" + System.currentTimeMillis());
                System.out.println(sendResult.toString());
            }
    
            @Override
            public void onException(Throwable e) {
                System.out.println("异步延迟消息发送失败！");
                e.printStackTrace();
            }
        }, 3000, 3);
    }
    
    public void sendTransactionalMessage() {
        // 第1步：发送事务消息，为了能识别出某个事务消息对应的本地事务，此处生成一个uuid设置到消息头上
        String txId = UUID.randomUUID().toString();
        rocketMQTemplate.sendMessageInTransaction("test-transactional",
                MessageBuilder.withPayload("事务消息").setHeaderIfAbsent("txId", txId).build(), txId);
        // 第三个参数可以传入任意参数，此处来传入对应的事务标识uuid（根据业务需求来，通常不传）
    }
    
    public void sendUpdateTransactionalMessage() {
        // 换一个Template发送
        String txId = UUID.randomUUID().toString();
        dataUpdateRocketMQTemplate.sendMessageInTransaction("test-transactionalupdate",
                MessageBuilder.withPayload("update事务消息").setHeaderIfAbsent("txId", txId).build(), null);
    }
    
    
    public void send10Message() {
        for (int i = 0; i < 10; i++) {
            rocketMQTemplate.syncSend("test-ordered", "message" + i);
        }
    }
    public void sendOrderedMessage() {
        for (int i = 0; i < 10; i++) {
            rocketMQTemplate.syncSendOrderly("test-ordered", "ordered-message" + i, "ordered");
        }
    }
}
