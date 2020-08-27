package com.tx.quartz.db.dao;

import com.tx.quartz.domain.JobEntity;
import org.apache.ibatis.annotations.Mapper;

;

@Mapper
public interface JobDao {
    void delete(Integer id);

    void insert(JobEntity entity);

}