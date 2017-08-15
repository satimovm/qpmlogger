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
               "\t\t\t\t\t\tjobGroup:\t\t\t" + jobGroup +"\n"+
               "\t\t\t\t\t\tjobName:\t\t\t" + jobName + "\n" +
               "\t\t\t\t\t\ttriggerGroup:\t\t" + triggerGroup +"\n"+
               "\t\t\t\t\t\ttriggerName:\t\t" + triggerName + "\n" +
               "\t\t\t\t\t\tfireTime:\t\t\t" + DateUtils.formatLong(fireTime) +"\n" +
               "\t\t\t\t\t\tnextFireTime:\t\t" + DateUtils.formatLong(nextFireTime) + "\n" +
               "\t\t\t\t\t\tjobRunTime:\t\t\t" + jobRunTime +" ms\n" +
               "\t\t\t\t\t}\n"+
               "***********************************************************************";

    }
}
