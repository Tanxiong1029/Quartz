package com.tx.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author：tanxiong
 * @Description：
 * @Date： 2020/8/28
 */

public class HelloJob implements Job {

    private final static Logger logger= LoggerFactory.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //获取任务名
        String taskName = context.getJobDetail().getKey().getName();
        String groupName=context.getJobDetail().getKey().getGroup();
        System.out.println(String.format("task--->%s,%s",taskName,groupName));
    }
}
