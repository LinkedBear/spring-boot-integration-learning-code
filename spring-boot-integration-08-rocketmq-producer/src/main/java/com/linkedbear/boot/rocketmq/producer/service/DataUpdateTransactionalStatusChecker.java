package com.linkedbear.boot.rocketmq.producer.service;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

import java.nio.charset.StandardCharsets;

@RocketMQTransactionListener(rocketMQTemplateBeanName = "dataUpdateRocketMQTemplate")
public class DataUpdateTransactionalStatusChecker implements RocketMQLocalTransactionListener {
    
    @Autowired
    private DataService dataService;
    
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String payload = new String((byte[]) msg.getPayload(), StandardCharsets.UTF_8);
        System.out.println(payload);
        System.out.println(arg);
    
        RocketMQLocalTransactionState state = RocketMQLocalTransactionState.COMMIT;
        try {
            dataService.updateData(msg.getHeaders().get("txId", String.class));
        } catch (Exception e) {
            state = RocketMQLocalTransactionState.UNKNOWN;
        }
        return state;
    }
    
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        System.out.println(new String((byte[]) msg.getPayload(), StandardCharsets.UTF_8));
        return dataService.contains(msg.getHeaders().get("txId", String.class))
                ? RocketMQLocalTransactionState.COMMIT
                : RocketMQLocalTransactionState.ROLLBACK;
    }
}
