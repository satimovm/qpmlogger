package com.qpmLogger.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * User: satimov
 * Date: 8/7/17 11:26 PM
 */
@Getter
@Setter
public class JobEventTO {
    private String calendarName;
    private String jobGroup;
    private String jobName;
    private String schedulerName;
    private String triggerGroup;
    private String triggerName;
    private Date fireTime;
    private Date nextFireTime;
    private Date previousFireTime;
    private Date scheduledFireTime;
    private boolean recovering;
    private long jobRunTime;
    private int refireCount;
    private String schedulerId;
    private String quartzInstanceId;
}