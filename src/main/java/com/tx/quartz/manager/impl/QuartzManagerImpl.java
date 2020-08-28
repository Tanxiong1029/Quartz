package com.tx.quartz.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tx.quartz.db.service.JobService;
import com.tx.quartz.domain.JobEntity;
import com.tx.quartz.manager.QuartzManager;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class QuartzManagerImpl implements QuartzManager {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobService jobService;

    private final static Logger logger= LoggerFactory.getLogger(QuartzManagerImpl.class);

    @Override
    public boolean addJob(JobEntity entity) {
        boolean flag=true;
        try {
            //构建Job信息
            JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(entity.getClazz()))
                    .withIdentity(entity.getJobName(), entity.getGroupName())
                    .withDescription(entity.getDescInfo())
                    .build();

            //构建cron表达式
            CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(entity.getCronExp());

            //构建Trigger
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(entity.getJobName(), entity.getGroupName())
                    .withSchedule(cronSchedule)
                    .build();

            //Job参数
            if(StringUtils.hasText(entity.getParam())){
                Map<String, Object> map = JSON.parseObject(entity.getParam(), new TypeReference<Map<String, Object>>() {
                });
                cronTrigger.getJobDataMap().putAll(map);
            }
            scheduler.scheduleJob(jobDetail,cronTrigger);
            //启动调度器
            scheduler.start();

            //将任务写入数据库
            //jobService.insert(entity);
        } catch (ClassNotFoundException e) {
            flag=false;
            logger.error("Job class not find ==>{}",e);
        }catch (SchedulerException e){
            flag=false;
            logger.error("build scheduleJob error by jobDerail and trigger ==>{}",e);
        }
        return flag;
    }

    @Override
    public void pauseJob(JobEntity entity) {

    }

    @Override
    public void resumeJob(JobEntity entity) {

    }

    @Override
    public void updateJob(JobEntity entity) {

    }

    @Override
    public void deleteJob(JobEntity entity) {

    }

    @Override
    public void startAllJobs() {

    }

    @Override
    public void shutdownAllJobs() {

    }
}
