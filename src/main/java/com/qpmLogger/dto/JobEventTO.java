package com.qpmLogger.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * User: satimov
 * Date: 8/7/17 11:26 PM
 */
@Getter
@Setter
@ToString
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
    private Long jobRunTime;
    private Integer refireCount;
    private String schedulerId;
    private String quartzInstanceId;
    private String type;
}
