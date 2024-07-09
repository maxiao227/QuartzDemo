package com.hisense.quartzdemo.job;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JobDetail1 {

    public void deal(String name) {
        System.out.println("定时任务动物组工作开始：" + name + " 当前时间：" + LocalDateTime.now());
        try {
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("定时任务动物组工作结束：" + name + " 当前时间：" + LocalDateTime.now());
    }
}
