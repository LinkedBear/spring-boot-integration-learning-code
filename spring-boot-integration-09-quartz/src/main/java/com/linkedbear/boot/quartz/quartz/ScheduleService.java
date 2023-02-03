package com.linkedbear.boot.quartz.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleService.class);
    
    @Scheduled(cron = "0/5 * * * * *")
    public void test() {
        LOGGER.info("ScheduleService test invoke ......");
    }
}
