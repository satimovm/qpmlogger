package com.qpmLogger.datasource.postgres.db;

import com.qpmLogger.datasource.mongo.db.JobMongoDomain;
import com.qpmLogger.dto.JobEventTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.Optional;

/**
 * User: satimov
 * Date: 8/9/17 1:58 PM
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseJobEventDomain extends BaseDomain {
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
        this.setRecovering(Optional.ofNullable(item.getRecovering()).orElse(false));
        this.setJobRunTime(Optional.ofNullable(item.getJobRunTime()).orElse(0L));
        this.setRefireCount(Optional.ofNullable(item.getRefireCount()).orElse(0));
        this.setSchedulerId(item.getSchedulerId());
        this.setQuartzInstanceId(item.getQuartzInstanceId());
        this.setType(item.getType());
        return this;
    }

    public JobMongoDomain toMongoEntity() {
        final JobMongoDomain mongoDomain = new JobMongoDomain();

        mongoDomain.setId(this.getId());
        mongoDomain.setCalendarName(this.getCalendarName());
        mongoDomain.setJobGroup(this.getJobGroup());
        mongoDomain.setJobName(this.getJobName());
        mongoDomain.setSchedulerName(this.getSchedulerName());
        mongoDomain.setTriggerGroup(this.getTriggerGroup());
        mongoDomain.setTriggerName(this.getTriggerName());
        mongoDomain.setFireTime(this.getFireTime());
        mongoDomain.setNextFireTime(this.getNextFireTime());
        mongoDomain.setPreviousFireTime(this.getPreviousFireTime());
        mongoDomain.setScheduledFireTime(this.getScheduledFireTime());
        mongoDomain.setRecovering(this.isRecovering());
        mongoDomain.setJobRunTime(this.getJobRunTime());
        mongoDomain.setRefireCount(this.getRefireCount());
        mongoDomain.setSchedulerId(this.getSchedulerId());
        mongoDomain.setQuartzInstanceId(this.getQuartzInstanceId());
        mongoDomain.setType(this.getType());
        return mongoDomain;
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
