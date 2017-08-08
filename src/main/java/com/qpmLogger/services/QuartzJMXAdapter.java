package com.qpmLogger.services;

import com.qpmLogger.dto.JobTO;
import com.qpmLogger.dto.QuartzInstanceTO;
import com.qpmLogger.dto.SchedulerTO;
import com.qpmLogger.dto.TriggerTO;

import javax.management.ObjectName;
import java.util.List;

/**
 * User: satimov
 * Date: 8/8/17 2:59 PM
 */
public interface QuartzJMXAdapter {
    String getVersion(QuartzInstanceTO quartzInstance, ObjectName objectName) throws Exception;

    void printAttributes(QuartzInstanceTO quartzInstance, ObjectName objectName) throws Exception;

    void printConstructors(QuartzInstanceTO quartzInstance, ObjectName objectName) throws Exception;

    void printOperations(QuartzInstanceTO quartzInstance, ObjectName objectName) throws Exception;

    void printNotifications(QuartzInstanceTO quartzInstance, ObjectName objectName) throws Exception;

    void printClassName(QuartzInstanceTO quartzInstance, ObjectName objectName) throws Exception;

    SchedulerTO populateScheduler(QuartzInstanceTO quartzInstance, ObjectName objectName) throws Exception;

    List<JobTO> getJobDetails(QuartzInstanceTO quartzInstance, String scheduleID) throws Exception;

    SchedulerTO getScheduler(QuartzInstanceTO quartzInstance, String scheduleID) throws Exception;

    List<TriggerTO> getTriggersForJob(QuartzInstanceTO quartzInstance, String scheduleID, String jobName, String groupName) throws Exception;
}
