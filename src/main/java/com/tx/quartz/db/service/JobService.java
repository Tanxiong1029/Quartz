package com.tx.quartz.db.service;

import com.tx.quartz.db.dao.JobDao;
import com.tx.quartz.domain.JobEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther tanxiong
 * @date 2020/8/30
 **/
@Service
public class JobService {

    @Autowired(required = false)
    private JobDao dao;


    public void del(Integer id) {
        dao.delete(id);
    }


    public void insert(JobEntity entity) {
        dao.insert(entity);
    }


    public void update(JobEntity entity) {
        dao.update(entity);
    }


    public JobEntity get(JobEntity entity) {
        return dao.get(entity);
    }
}
