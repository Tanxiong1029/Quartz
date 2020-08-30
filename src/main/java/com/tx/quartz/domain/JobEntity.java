package com.tx.quartz.domain;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class JobEntity {

    private Integer id;

    //任务类全限定名
    private String clazz;

    //任务名称
    private String jobName;

    //所属组
    private String groupName;

    //cron表达式
    private String cronExp;

    //参数
    private String param;

    //任务描述
    private String descInfo;

    //任务状态
    private String jobStatus;

    //创建时间
    private Date createTime;
}
