package com.linkedbear.boot.quartz.controller;

import com.linkedbear.boot.quartz.quartz.SimpleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class DynamicScheduleController {
    
    @Autowired
    private Scheduler scheduler;
    
    @GetMapping("/addSchedule")
    public String addSchedule() throws SchedulerException {
        int random = ThreadLocalRandom.current().nextInt(1000);
        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("test-schedule" + random, "test-group").build();
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule("0/3 * * * * ?");
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("test-trigger" + random, "test-trigger-group")
                .withSchedule(cron).build();
        scheduler.scheduleJob(jobDetail, trigger);
        return "success";
    }
    
    @GetMapping("/pauseSchedule")
    public String pauseSchedule(String jobName, String jobGroup) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        // 获取定时任务
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return "error";
        }
        scheduler.pauseJob(jobKey);
        return "success";
    }
    
    @GetMapping("/remuseSchedule")
    public String remuseSchedule(String jobName, String jobGroup) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        // 获取定时任务
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return "error";
        }
        scheduler.pauseJob(jobKey);
        return "success";
    }
    
    @GetMapping("/removeSchedule")
    public String removeSchedule(String jobName, String jobGroup, String triggerName, String triggerGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        Trigger trigger = scheduler.getTrigger(triggerKey);
        if (trigger == null) {
            return "error";
        }
        // 停止触发器
        scheduler.pauseTrigger(triggerKey);
        // 移除触发器
        scheduler.unscheduleJob(triggerKey);
        // 删除任务
        scheduler.deleteJob(jobKey);
        return "success";
    }
}
