package com.hisense.quartzdemo.entity;

import lombok.Data;

@Data
public class JobConfiguration {
    private Integer id;
    private String jobName;
    private String jobGroup;
    private String cronExpression;
    private String jobClass;
}