package com.linkedbear.boot.quartz.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleJob implements Job {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public void execute(JobExecutionContext context) {
        logger.info("简单任务执行 ...... " + context.getJobDetail().getKey().getName());
    }
}
