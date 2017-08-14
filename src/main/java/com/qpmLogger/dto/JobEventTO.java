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
        return "\n" +
               "*****************************************************************************\n" +
               "*\t\tJobEventTO(jobGroup: " + jobGroup +"\n"+
               "*\t\tjobName: " + jobName + "\n" +
               "*\t\ttriggerGroup: " + triggerGroup +"\n"+
               "*\t\ttriggerName: " + triggerName + "\n" +
               "*\t\tfireTime: " + DateUtils.formatLong(fireTime) +"\n" +
               "*\t\tnextFireTime " + DateUtils.formatLong(nextFireTime) + "\n" +
               "*\t\tjobRunTime: " + jobRunTime +" ms);\n"+
               "*****************************************************************************";

    }
}
