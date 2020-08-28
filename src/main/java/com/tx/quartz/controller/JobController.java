package com.tx.quartz.controller;

import com.alibaba.fastjson.JSON;
import com.tx.quartz.domain.JobEntity;
import com.tx.quartz.manager.QuartzManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author：tanxiong
 * @Description：
 * @Date： 2020/8/28
 */
@RestController
public class JobController {
    @Autowired
    private QuartzManager quartzManager;
    
    @RequestMapping("/test")
    public boolean test(){
        JobEntity entity=new JobEntity();
        Map<String,Object> map=new HashMap<>();
        map.put("createTime",new Date());
        entity.setJobName("helloTask").setGroupName("task").setClazz("com.tx.quartz.job.HelloJob").setDescInfo("测试任务").setCronExp("0/2 * * * * ? ").setParam(JSON.toJSONString(map));
        return  quartzManager.addJob(entity);
    }
}
