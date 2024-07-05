package com.hisense.quartzdemo.controller;

import com.hisense.quartzdemo.service.SystemSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
    @Autowired
    SystemSchedulerService systemSchedulerService;

    @PostMapping("/add")
    public boolean add(@RequestParam String name, @RequestParam String group, @RequestParam String cron) {
        return systemSchedulerService.addScheduler(name, group, cron);
    }

    @PostMapping("/update")
    public boolean update(@RequestParam String name, @RequestParam String group, @RequestParam String cron) {
        return systemSchedulerService.updateScheduler(name, group, cron);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestParam String name, @RequestParam String group) {
        return systemSchedulerService.deleteScheduler(name, group);
    }
}
