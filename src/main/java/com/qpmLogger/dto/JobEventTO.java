package com.qpmLogger.dto;

import com.qpmLogger.utils.DateUtils;
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
    private Boolean recovering;
    private Long jobRunTime;
    private Integer refireCount;
    private String schedulerId;
    private String quartzInstanceId;
    private String type;

    @Override
    public String toString() {
        return "com.qpmLogger.dto.JobEventTO {\n" +
               "\t\t\t\t\t\tjobGroup: " + jobGroup +"\n"+
               "\t\t\t\t\t\tjobName: " + jobName + "\n" +
               "\t\t\t\t\t\ttriggerGroup: " + triggerGroup +"\n"+
               "\t\t\t\t\t\ttriggerName: " + triggerName + "\n" +
               "\t\t\t\t\t\tfireTime: " + DateUtils.formatLong(fireTime) +"\n" +
               "\t\t\t\t\t\tnextFireTime: " + DateUtils.formatLong(nextFireTime) + "\n" +
               "\t\t\t\t\t\tjobRunTime: " + jobRunTime +" ms\n" +
               "\t\t\t\t\t}\n"+
               "***********************************************************************";

    }
}
