package com.hisense.quartzdemo.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;


public class QuartzSchedulerJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        switch (jobExecutionContext.getJobDetail().getKey().getGroup()) {
            case "动物组":
                System.out.println("定时任务动物组工作：" + jobExecutionContext.getJobDetail().getKey().getName()
                        + " 当前时间：" + LocalDateTime.now());
                break;
            case "机器人组":
                System.out.println("定时任务机器人组工作：" + jobExecutionContext.getJobDetail().getKey().getName()
                        + " 当前时间：" + LocalDateTime.now());
                break;
        }

    }
}
