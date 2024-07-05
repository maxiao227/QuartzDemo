package com.hisense.quartzdemo.service.impl;


import com.hisense.quartzdemo.job.QuartzSchedulerJob;
import com.hisense.quartzdemo.service.SystemSchedulerService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemSchedulerServiceImpl implements SystemSchedulerService {
    @Autowired
    Scheduler scheduler;

    @Override
    public boolean addScheduler(String name, String group, String cron) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(QuartzSchedulerJob.class)//绑定一个具体工作类
                    .withIdentity(name, group) //设置一个标识，分组以及名字
                    .usingJobData("jobData", name) //传递到具体工作的数据，可以多个，获取根据dataKey获取即可
                    .usingJobData("executeData", "测试")
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(name, group)//设置一个触发器标识,这里设置了跟JobDetail使用一样的命名以及分组
                    .forJob(jobDetail)//绑定trigger到jobdetail
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateScheduler(String jobDetailName, String jobDetailGroup, String cron) {
        try {
            JobKey jobKey = JobKey.jobKey(jobDetailName, jobDetailGroup);
            if (!CronExpression.isValidExpression(cron) || !scheduler.checkExists(jobKey)) {
                return false;
            }
            //triggerKey为添加定时任务时配置的name,group，这里是添加的时候设置的name跟group跟jobdetail是一样的
            TriggerKey triggerKey = TriggerKey.triggerKey(jobDetailName, jobDetailGroup);
            Trigger newTrigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();

//            CronTrigger newTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
//            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            scheduler.rescheduleJob(triggerKey, newTrigger);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteScheduler(String jobDetailName, String jobDetailGroup) {
        try {
            JobKey jobKey = JobKey.jobKey(jobDetailName, jobDetailGroup);
            if (!scheduler.checkExists(jobKey)) {
                return false;
            }
            scheduler.deleteJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean puaseScheduler(String jobDetailName, String jobDetailGroup) {
        try {
            JobKey jobKey = JobKey.jobKey(jobDetailName, jobDetailGroup);
            if (!scheduler.checkExists(jobKey)) {
                return false;
            }
            scheduler.pauseJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean resumeScheduler(String jobDetailName, String jobDetailGroup) {
        try {
            JobKey jobKey = JobKey.jobKey(jobDetailName, jobDetailGroup);
            if (!scheduler.checkExists(jobKey)) {
                return false;
            }
            scheduler.resumeJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }
}