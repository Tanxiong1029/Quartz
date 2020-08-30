package com.tx.quartz.db.dao;

import com.tx.quartz.domain.JobEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobDao {
    /**
     *  根据任务id删除一个任务
     * @param id 任务id
     */
    void delete(Integer id);

    /**
     *  添加一个任务到数据库
     * @param entity 任务信息
     */
    void insert(JobEntity entity);

    /**
     * 根据任务id更新任务
     * @param entity 任务信息
     */
    void update(JobEntity entity);

    /**
     * 根据任务id获取任务
     * @param entity
     * @return
     */
    JobEntity get(JobEntity entity);

}