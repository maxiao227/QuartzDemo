package com.hisense.quartzdemo.job;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Component
public class QuartzSchedulerJob extends QuartzJobBean {
    @Autowired
    private JobDetail1 jobDetail1;
    @Autowired
    private JobDetail2 jobDetail2;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        switch (jobExecutionContext.getJobDetail().getKey().getGroup()) {
            case "动物组":
                CompletableFuture.runAsync(() -> jobDetail1.deal(jobExecutionContext.getJobDetail().getKey().getName()));
//                jobDetail1.deal(jobExecutionContext.getJobDetail().getKey().getName());
                break;
            case "机器人组":
                CompletableFuture.runAsync(() -> jobDetail2.deal(jobExecutionContext.getJobDetail().getKey().getName()));
//                jobDetail2.deal(jobExecutionContext.getJobDetail().getKey().getName());
                break;
            default:
                System.out.println("定时任务工作：" + jobExecutionContext.getJobDetail().getKey().getName()
                        + " 当前时间：" + LocalDateTime.now());
        }
    }
}
