package com.tx.quartz.manager.impl;

import com.tx.quartz.db.service.JobService;
import com.tx.quartz.domain.JobEntity;
import com.tx.quartz.service.ScheduleJobService;
import com.tx.quartz.manager.QuartzManager;
import com.tx.quartz.msg.QuartzCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuartzManagerImpl implements QuartzManager {

    @Autowired
    private ScheduleJobService scheduleJobService;

    @Autowired
    private JobService jobService;

    private final static Logger logger= LoggerFactory.getLogger(QuartzManagerImpl.class);

    @Override
    public boolean addJob(JobEntity entity) {
        //TODO 这里可以根据业务扩展,进行任务是否重复等等处理
        boolean flag=true;
        jobService.insert(entity);
        if(QuartzCode.Job.START_JOB_STATUS.equals(entity.getJobStatus())){
            flag = scheduleJobService.schedulerAddJob(entity);
        }
        return flag;
    }

    @Override
    public boolean pauseJob(JobEntity entity) {
        JobEntity dbJob = jobService.get(entity);
        boolean b = scheduleJobService.schedulerPauseJob(dbJob);
        if(b){
            entity.setJobStatus(QuartzCode.Job.STOP_JOB_STATUS);
        }
        return b;
    }

    @Override
    public boolean resumeJob(JobEntity entity) {
        JobEntity dbJob = jobService.get(entity);
        boolean b = scheduleJobService.schedulerResumeJob(dbJob);
        if(b){
            entity.setJobStatus(QuartzCode.Job.STOP_JOB_STATUS);
        }
        return b;

    }

    @Override
    public boolean updateJob(JobEntity entity) {
        jobService.update(entity);
        JobEntity dbJob = jobService.get(entity);
        return scheduleJobService.schedulerUpdateJob(dbJob);
    }

    @Override
    public boolean deleteJob(JobEntity entity) {
        JobEntity dbJob = jobService.get(entity);
        boolean b = scheduleJobService.schedulerDeleteJob(dbJob);
        if(b){
            jobService.del(entity.getId());
        }
        return b;
    }

}
