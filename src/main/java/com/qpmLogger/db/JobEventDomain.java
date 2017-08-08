package com.qpmLogger.db;

import com.qpmLogger.constants.TableNameConstants;
import com.qpmLogger.dto.JobEventTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * User: Satimov Murad
 * Date: 8/7/17 11:07 PM
 */
@Getter
@Setter
@Entity
@Table(name = TableNameConstants.JobEvent)
public class JobEventDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public void fromTO(JobEventTO item) {
        if (item == null) {
            return;
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
