package com.linkedbear.boot.xxljob.service;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    
    @XxlJob("demoTest")
    public void test() {
        System.out.println("触发定时任务 。。。");
    }
}
