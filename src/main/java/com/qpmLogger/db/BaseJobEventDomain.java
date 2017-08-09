package com.qpmLogger.db;

import com.qpmLogger.dto.JobEventTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * User: satimov
 * Date: 8/9/17 1:58 PM
 */
@Getter
@Setter
@MappedSuperclass
public class BaseJobEventDomain extends BaseDomain {
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
    private String type;

    public BaseJobEventDomain fromTO(JobEventTO item) {
        if (item == null) {
            return null;
        }
        this.setCalendarName(item.getCalendarName());
        this.setJobGroup(item.getJobGroup());
        this.setJobName(item.getJobName());
        this.setSchedulerName(item.getSchedulerName());
        this.setTriggerGroup(item.getTriggerGroup());
        this.setTriggerName(item.getTriggerName());
        this.setFireTime(item.getFireTime());
        this.setNextFireTime(item.getNextFireTime());
        this.setPreviousFireTime(item.getPreviousFireTime());
        this.setScheduledFireTime(item.getScheduledFireTime());
        this.setRecovering(item.isRecovering());
        this.setJobRunTime(item.getJobRunTime());
        this.setRefireCount(item.getRefireCount());
        this.setSchedulerId(item.getSchedulerId());
        this.setQuartzInstanceId(item.getQuartzInstanceId());
        this.setType(item.getType());
        return this;
    }

    public JobEventTO toTO() {
        final JobEventTO item = new JobEventTO();

        item.setCalendarName(this.getCalendarName());
        item.setJobGroup(this.getJobGroup());
        item.setJobName(this.getJobName());
        item.setSchedulerName(this.getSchedulerName());
        item.setTriggerGroup(this.getTriggerGroup());
        item.setTriggerName(this.getTriggerName());
        item.setFireTime(this.getFireTime());
        item.setNextFireTime(this.getNextFireTime());
        item.setPreviousFireTime(this.getPreviousFireTime());
        item.setScheduledFireTime(this.getScheduledFireTime());
        item.setRecovering(this.isRecovering());
        item.setJobRunTime(this.getJobRunTime());
        item.setRefireCount(this.getRefireCount());
        item.setSchedulerId(this.getSchedulerId());
        item.setQuartzInstanceId(this.getQuartzInstanceId());
        return item;
    }
}
