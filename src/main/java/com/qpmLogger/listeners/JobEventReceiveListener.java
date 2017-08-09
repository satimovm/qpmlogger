package com.qpmLogger.listeners;

import com.qpmLogger.configuration.ApplicationContextProvider;
import com.qpmLogger.dto.JobEventTO;
import com.qpmLogger.services.JobEventService;
import com.qpmLogger.utils.DataGetter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeDataSupport;

/**
 * User: Satimov Murad
 * Date: 8/7/17 11:24 PM
 */
@Component
public class JobEventReceiveListener implements NotificationListener {

    @Getter
    @Setter
    private String UUID;
    @Autowired
    private JobEventService jobEventService =
            (JobEventService) ApplicationContextProvider.applicationContext.getBean("jobEventService");

    @Override
    public void handleNotification(Notification notification, Object handback) {
        if (notification == null) {
            return;
        }
        final Object object = notification.getUserData();

        if (object == null || !(object instanceof CompositeDataSupport)) {
            return;
        }
        try {
            final CompositeDataSupport compositeDataSupport = (CompositeDataSupport) object;
            final JobEventTO event = new JobEventTO();

            event.setJobName(DataGetter.tryGetString(this.getKey(compositeDataSupport, "jobName")));
            event.setCalendarName(DataGetter.tryGetString(this.getKey(compositeDataSupport, "calendarName")));
            event.setFireTime(DataGetter.tryGetDate(this.getKey(compositeDataSupport, "fireTime")));
            event.setJobGroup(DataGetter.tryGetString(this.getKey(compositeDataSupport, "jobGroup")));
            event.setJobRunTime(DataGetter.tryGetLong(this.getKey(compositeDataSupport, "jobRunTime")));
            event.setNextFireTime(DataGetter.tryGetDate(this.getKey(compositeDataSupport, "nextFireTime")));
            event.setPreviousFireTime(DataGetter.tryGetDate(this.getKey(compositeDataSupport, "previousFireTime")));
            event.setRecovering(DataGetter.tryGetBoolean(this.getKey(compositeDataSupport, "recovering")));
            event.setRefireCount(DataGetter.tryGetInt(this.getKey(compositeDataSupport, "refireCount")));
            event.setScheduledFireTime(DataGetter.tryGetDate(this.getKey(compositeDataSupport, "scheduledFireTime")));
            event.setSchedulerName(DataGetter.tryGetString(this.getKey(compositeDataSupport, "schedulerName")));
            event.setTriggerGroup(DataGetter.tryGetString(this.getKey(compositeDataSupport, "triggerGroup")));
            event.setTriggerName(DataGetter.tryGetString(this.getKey(compositeDataSupport, "triggerName")));
            event.setType(notification.getType());
            final String[] arr = this.getUUID().split("@@");

            if (arr.length == 2) {
                final String uuid = arr[0];
                final String scheduleID = arr[1];

                event.setSchedulerId(scheduleID);
                event.setQuartzInstanceId(uuid);
            }
            if ("jobWasExecuted".equalsIgnoreCase(notification.getType())) {
                jobEventService.saveExecutedEvent(event);
            }
            jobEventService.saveJobEvent(event);
        } catch (Exception t) {
            t.printStackTrace();
        }
    }

    private Object getKey(CompositeDataSupport data, String key) {
        if (data == null || StringUtils.isEmpty(key) || !data.containsKey(key)) {
            return null;
        }
        return data.get(key);
    }
}