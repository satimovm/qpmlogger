package com.qpmLogger.listeners;

import com.qpmLogger.dto.JobEventTO;
import com.qpmLogger.services.JobEventService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeDataSupport;
import java.util.Date;

/**
 * User: Satimov MUrad
 * Date: 8/7/17 11:24 PM
 */
@Component
public class JobEventReceiveListener implements NotificationListener {

    private final JobEventService jobEventService;

    @Getter
    @Setter
    private String UUID;

    @Autowired
    public JobEventReceiveListener(JobEventService jobEventService) {
        this.jobEventService = jobEventService;
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {
        if (notification == null || !"jobWasExecuted".equalsIgnoreCase(notification.getType())) {
            return;
        }
        final Object object = notification.getUserData();

        if (object == null || !(object instanceof CompositeDataSupport)) {
            return;
        }
        try {
            final CompositeDataSupport compositeDataSupport = (CompositeDataSupport) object;
            final JobEventTO event = new JobEventTO();

            event.setJobName((String) compositeDataSupport.get("jobName"));
            event.setCalendarName((String) compositeDataSupport.get("calendarName"));
            event.setFireTime((Date) compositeDataSupport.get("fireTime"));
            event.setJobGroup((String) compositeDataSupport.get("jobGroup"));
            event.setJobRunTime((Long) compositeDataSupport.get("jobRunTime"));
            event.setNextFireTime((Date) compositeDataSupport.get("nextFireTime"));
            event.setPreviousFireTime((Date) compositeDataSupport.get("previousFireTime"));
            event.setRecovering((Boolean) compositeDataSupport.get("recovering"));
            event.setRefireCount((Integer) compositeDataSupport.get("refireCount"));
            event.setScheduledFireTime((Date) compositeDataSupport.get("scheduledFireTime"));
            event.setSchedulerName((String) compositeDataSupport.get("schedulerName"));
            event.setTriggerGroup((String) compositeDataSupport.get("triggerGroup"));
            event.setTriggerName((String) compositeDataSupport.get("triggerName"));
            final String[] arr = this.UUID.split("@@");

            if (arr.length == 2) {
                final String uuid = arr[0];
                final String scheduleID = arr[1];

                event.setSchedulerId(scheduleID);
                event.setQuartzInstanceId(uuid);
            }
            jobEventService.saveNewEvent(event);
        } catch (Exception t) {
            t.printStackTrace();
        }
    }
}
