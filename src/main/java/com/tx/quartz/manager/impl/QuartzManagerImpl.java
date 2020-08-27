package com.tx.quartz.manager.impl;

import com.tx.quartz.domain.JobEntity;
import com.tx.quartz.manager.QuartzManager;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;

public class QuartzManagerImpl implements QuartzManager {

    @Autowired
    private Scheduler scheduler;

    @Override
    public boolean addJob(JobEntity entity) {
        return false;
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
