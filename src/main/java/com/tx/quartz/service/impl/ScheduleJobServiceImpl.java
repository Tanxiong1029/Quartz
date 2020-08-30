package com.tx.quartz.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tx.quartz.domain.JobEntity;
import com.tx.quartz.service.ScheduleJobService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @auther tanxiong
 * @date 2020/8/30
 **/
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    @Autowired
    private Scheduler scheduler;

    private final Logger logger= LoggerFactory.getLogger(this.getClass());


    @Override
    public  boolean schedulerAddJob(JobEntity entity){
        boolean flag=true;
        try {
            //启动调度器
            scheduler.start();

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
                Map<String, Object> map = JSON.parseObject(entity.getParam(), new TypeReference<Map<String, Object>>() {});
                cronTrigger.getJobDataMap().putAll(map);
            }
            scheduler.scheduleJob(jobDetail,cronTrigger);
        } catch (ClassNotFoundException e) {
            flag=false;
            logger.error("Job class not find exception ==>{}",e);
        }catch (SchedulerException e){
            flag=false;
            logger.error("build scheduleJob error by jobDerail and trigger exception ==>{}",e);
        }
        return flag;
    }

    @Override
    public boolean schedulerPauseJob(JobEntity entity) {
        boolean flag=true;
        try {
            scheduler.pauseJob(JobKey.jobKey(entity.getJobName(),entity.getGroupName()));
        } catch (SchedulerException e) {
            flag=false;
            logger.error("stop job exception ==>{}",e);
        }
        return flag;
    }

    @Override
    public boolean schedulerResumeJob(JobEntity entity) {
        boolean flag=true;
        try {
            scheduler.resumeJob(JobKey.jobKey(entity.getJobName(),entity.getGroupName()));
        } catch (SchedulerException e) {
            flag=false;
            logger.error("resume job exception ==>{}",e);
        }
        return flag;
    }

    @Override
    public boolean schedulerUpdateJob(JobEntity entity) {
        boolean flag=true;
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(entity.getJobName(), entity.getGroupName());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (StringUtils.hasText(entity.getCronExp())) {
                // 表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(entity.getCronExp());
                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            }

            //修改map
            if (StringUtils.hasText(entity.getParam())) {
                Map<String, Object> map = JSON.parseObject(entity.getParam(), new TypeReference<Map<String, Object>>() {});
                trigger.getJobDataMap().putAll(map);
            }
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (Exception e) {
            flag=false;
            logger.error("update job exception ==>{}",e);
        }
        return flag;
    }

    @Override
    public boolean schedulerDeleteJob(JobEntity entity) {
        boolean flag=true;
        try {
            //停止Trigger
            scheduler.pauseTrigger(TriggerKey.triggerKey(entity.getJobName(), entity.getGroupName()));
            //移除Trigger
            scheduler.unscheduleJob(TriggerKey.triggerKey(entity.getJobName(), entity.getGroupName()));
            //删除Job
            scheduler.deleteJob(JobKey.jobKey(entity.getJobName(), entity.getGroupName()));
        } catch (Exception e) {
            flag=false;
            logger.error("del job exception ==>{}",e);
        }
        return flag;
    }
}
