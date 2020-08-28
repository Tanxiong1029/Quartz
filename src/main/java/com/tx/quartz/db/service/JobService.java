package com.tx.quartz.db.service;

import com.tx.quartz.db.dao.JobDao;
import com.tx.quartz.domain.JobEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：tanxiong
 * @Description：
 * @Date： 2020/8/28
 */
@Service
public class JobService {

    @Autowired(required = false)
    private JobDao dao;

    public void insert(JobEntity entity){
        dao.insert(entity);
    }
}
