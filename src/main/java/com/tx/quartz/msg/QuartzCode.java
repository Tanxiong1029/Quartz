package com.tx.quartz.msg;

/**
 * @auther tanxiong
 * @date 2020/8/30
 **/
public interface QuartzCode {
    String success = "0";

    String error = "-1";

    interface Job {
        String START_JOB_STATUS = "0";
        String STOP_JOB_STATUS = "1";

    }
}
