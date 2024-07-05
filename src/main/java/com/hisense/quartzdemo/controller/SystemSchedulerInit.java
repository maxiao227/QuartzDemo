package com.hisense.quartzdemo.controller;


import com.hisense.quartzdemo.service.SystemSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemSchedulerInit implements ApplicationRunner {
    @Autowired
    SystemSchedulerService systemSchedulerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        systemSchedulerService.addScheduler("小猪佩奇", "动物组", "0/5 * * * * ?");
//        systemSchedulerService.addScheduler("阿童木", "机器人组", "0/5 * * * * ?");
    }
}