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

    @RequestMapping("/addJob")
    public boolean addJob() {
        JobEntity entity = new JobEntity();
        Map<String, Object> map = new HashMap<>();
        map.put("createTime", new Date());
        entity.setJobName("helloTask").setJobStatus("0").setGroupName("task").setClazz("com.tx.quartz.job.HelloJob").setDescInfo("测试任务").setCronExp("0/2 * * * * ? ").setParam(JSON.toJSONString(map));
        return quartzManager.addJob(entity);
    }

    @RequestMapping("/stopJOb")
    public boolean stopJob() {
        JobEntity entity = new JobEntity();
        entity.setId(1);
        return quartzManager.pauseJob(entity);
    }

    @RequestMapping("/resumeJob")
    public boolean resumeJob() {
        JobEntity entity = new JobEntity();
        entity.setId(1);
        return quartzManager.resumeJob(entity);
    }

    @RequestMapping("updateJob")
    public boolean updateJob() {
        JobEntity entity = new JobEntity();
        entity.setId(1);
        entity.setCronExp("0/4 * * * * ? ");
        return quartzManager.updateJob(entity);
    }

    @RequestMapping("delJob")
    public boolean delJob() {
        JobEntity entity = new JobEntity();
        entity.setId(1);
        return quartzManager.deleteJob(entity);
    }

}
