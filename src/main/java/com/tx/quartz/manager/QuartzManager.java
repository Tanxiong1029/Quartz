package com.tx.quartz.manager;

import com.tx.quartz.domain.JobEntity;

import java.util.Map;

public interface QuartzManager {
    /**
     * 添加任务
     * @param entity 任务信息
     * @return
     */
    boolean addJob(JobEntity entity);


    /**
     * 暂停任务
     *
     * @param entity
     */
    boolean pauseJob(JobEntity entity);

    /**
     * 恢复任务
     *
     * @param entity
     */
    boolean resumeJob(JobEntity entity);

    /**
     * 更新任务
     *
     * @param entity
     */
    boolean updateJob(JobEntity entity);

    /**
     * 删除任务
     *
     * @param entity
     */
    boolean deleteJob(JobEntity entity);

}