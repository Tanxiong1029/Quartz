package com.tx.quartz.domain;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class JobEntity {
    private Integer id;
    private String clazz;//任务类全限定名
    private String jobName;//任务名称
    private String groupName;//所属组
    private String cronExp;//cron表达式
    private String param;//参数
    private String descInfo;//任务描述
    private String jobStatus;//任务状态
    private Date createTime;//创建时间
}
