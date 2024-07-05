package com.hisense.quartzdemo.service;


import com.hisense.quartzdemo.entity.JobConfiguration;
import com.hisense.quartzdemo.mapper.JobConfigurationMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Slf4j
public class JobConfigurationService {

    @Autowired
    private JobConfigurationMapper jobConfigurationMapper;
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void initJobs() {
        List<JobConfiguration> configurations = jobConfigurationMapper.selectAll();
        configurations.forEach(config -> {
            try {
                String jobClass = config.getJobClass();
                Object bean = applicationContext.getBean(jobClass);
                JobDetail jobDetail = JobBuilder.newJob(bean.getClass().asSubclass(Job.class))
                        .withIdentity(config.getJobName(), config.getJobGroup()).build();

                CronTrigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(config.getJobName(), config.getJobGroup())
                        .withSchedule(CronScheduleBuilder.cronSchedule(config.getCronExpression()))
                        .build();
                scheduler.scheduleJob(jobDetail, trigger);
            } catch (SchedulerException e) {
                log.error("Error scheduling job", e);
            }
        });
    }
}