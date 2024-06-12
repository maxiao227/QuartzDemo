package com.hisense.quartzdemo.mapper;

import com.hisense.quartzdemo.entity.JobConfiguration;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobConfigurationMapper {

    List<JobConfiguration> selectAll();

    void insert(JobConfiguration configuration);
}