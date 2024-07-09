package com.hisense.quartzdemo.job;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JobDetail2 {
    public void deal(String name) {
        System.out.println("定时任务机器人组工作：" + name + " 当前时间：" + LocalDateTime.now());
    }
}
