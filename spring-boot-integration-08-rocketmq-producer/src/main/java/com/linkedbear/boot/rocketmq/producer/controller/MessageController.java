package com.linkedbear.boot.rocketmq.producer.controller;

import com.linkedbear.boot.rocketmq.producer.service.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    
    @Autowired
    private MessageSender messageSender;
    
    @GetMapping("/sendMessage")
    public String sendMessage() {
        messageSender.sendMessage();
        //messageSender.sendOneway();
        return "success";
    }
    
    @GetMapping("/asyncSend")
    public String asyncSend() {
        messageSender.asyncSend();
        return "success";
    }
    
    @GetMapping("/sendTagsMessage")
    public String sendTagsMessage() {
        messageSender.sendTagsMessage();
        return "success";
    }
    
    @GetMapping("/sendDto")
    public String sendDto() {
        messageSender.sendDto();
        return "success";
    }
    
    @GetMapping("/sendDelayMessage")
    public String sendDelayMessage() {
        messageSender.sendDelayMessage();
        return "success";
    }
    
    @GetMapping("/sendTransactionalMessage")
    public String sendTransactionalMessage() {
        messageSender.sendTransactionalMessage();
        return "success";
    }
    
    @GetMapping("/sendUpdateTransactionalMessage")
    public String sendUpdateTransactionalMessage() {
        messageSender.sendUpdateTransactionalMessage();
        return "success";
    }
    
    @GetMapping("/send10Message")
    public String send10Message() {
        messageSender.send10Message();
        return "success";
    }
    @GetMapping("/sendOrderedMessage")
    public String sendOrderedMessage() {
        messageSender.sendOrderedMessage();
        return "success";
    }
}
