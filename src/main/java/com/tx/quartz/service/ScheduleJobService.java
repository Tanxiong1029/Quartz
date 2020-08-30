package com.tx.quartz.service;

        import com.tx.quartz.domain.JobEntity;

/**
 * @auther tanxiong
 * @date 2020/8/30
 **/
public interface ScheduleJobService {
    /**
     * 添加任务
     * @param entity 任务信息
     * @return boolean
     */
    boolean schedulerAddJob(JobEntity entity);

    /**
     * 暂停任务
     *
     * @param entity 任务信息
     * @return boolean
     */
    boolean schedulerPauseJob(JobEntity entity);

    /**
     * 恢复任务
     *
     * @param entity 任务信息
     * @return boolean
     */
    boolean schedulerResumeJob(JobEntity entity);

    /**
     * job 更新,更新频率和参数
     *
     * @param entity 任务信息（name、groupName、cronExp、param）
     * @return boolean
     */
    boolean schedulerUpdateJob(JobEntity entity);

    /**
     * 删除任务
     *
     * @param entity 任务信息
     * @return
     */
    boolean schedulerDeleteJob(JobEntity entity);

}
