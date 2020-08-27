package com.tx.quartz.config;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

@Configuration
public class QuartzConfig {

    private final  Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobFactory jobFactory;

    @Bean
    public SchedulerFactoryBean initSchedulerFactoryBean() {
        SchedulerFactoryBean factory = null;
        try {
            //获取配置属性
            PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
            propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
            //在quartz.properties中的属性被读取并注入后再初始化对象
            propertiesFactoryBean.afterPropertiesSet();
            //创建SchedulerFactoryBean
            factory = new SchedulerFactoryBean();
            //将配置文件中的信息添加到调度工厂中
            //对应java应用程序中的 new StdSchedulerFactory(properties)
            factory.setQuartzProperties(propertiesFactoryBean.getObject());
            factory.setJobFactory(jobFactory);
            // 默认的自动执行调度,这里设置为不自动执行调度，
            // 为了方便扩展集群分布式调度任务，这个服务只配置调度，另外启动一个或多个服务执行调度
            factory.setAutoStartup(false);
        } catch (IOException e) {
            logger.error("quartz properties load exception");
        }
        return factory;
    }

    /*
     * 通过schedulerFactoryBean获取Scheduler的实例
     */
    @Bean(name = "scheduler")
    public Scheduler getScheduler()  {
        return initSchedulerFactoryBean().getScheduler();
    }
}
