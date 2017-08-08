package com.qpmLogger.services.impl;

import com.google.common.collect.Lists;
import com.qpmLogger.dto.*;
import com.qpmLogger.services.QuartzJMXAdapter;
import com.qpmLogger.utils.QuartzConnectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.management.*;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.TabularDataSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * User: satimov
 * Date: 8/8/17 2:59 PM
 */
@Slf4j
@Service
public class QuartzJMXAdapterImpl implements QuartzJMXAdapter {

    @Override
    public String getVersion(QuartzInstanceTO quartzInstance, ObjectName objectName) throws Exception {
        if (quartzInstance == null || objectName == null) {
            return null;
        }
        final MBeanServerConnection connection = quartzInstance.getMBeanServerConnection();
        final String quartzVersion = (String) connection.getAttribute(objectName, "Version");

        if (!QuartzConnectUtil.isSupported(quartzVersion)) {
            log.error("Version:" + quartzVersion);
        }
        return quartzVersion;
    }

    @Override
    public void printAttributes(QuartzInstanceTO quartzInstance, ObjectName objectName) throws Exception {
        if (quartzInstance == null || objectName == null) {
            return;
        }
        final MBeanInfo info = quartzInstance.getMBeanServerConnection().getMBeanInfo(objectName);
        final MBeanAttributeInfo[] attributeInfos = info.getAttributes();

        for (MBeanAttributeInfo attributeInfo : attributeInfos) {
            System.out.println(attributeInfo.toString());
        }
    }

    @Override
    public void printConstructors(QuartzInstanceTO quartzInstance, ObjectName objectName) throws Exception {
        if (quartzInstance == null || objectName == null) {
            return;
        }
        final MBeanInfo info = quartzInstance.getMBeanServerConnection().getMBeanInfo(objectName);
        final MBeanConstructorInfo[] arr = info.getConstructors();

        for (MBeanConstructorInfo s : arr) {
            System.out.println(s.toString());
        }
    }

    @Override
    public void printOperations(QuartzInstanceTO quartzInstance, ObjectName objectName) throws Exception {
        if (quartzInstance == null || objectName == null) {
            return;
        }
        final MBeanInfo info = quartzInstance.getMBeanServerConnection().getMBeanInfo(objectName);
        final MBeanOperationInfo[] arr = info.getOperations();

        for (MBeanOperationInfo s : arr) {
            System.out.println(s.toString());
        }
    }

    @Override
    public void printNotifications(QuartzInstanceTO quartzInstance, ObjectName objectName) throws Exception {
        if (quartzInstance == null || objectName == null) {
            return;
        }
        final MBeanInfo info = quartzInstance.getMBeanServerConnection().getMBeanInfo(objectName);
        final MBeanNotificationInfo[] arr = info.getNotifications();
        for (MBeanNotificationInfo s : arr) {
            System.out.println(s.toString());
        }
    }

    @Override
    public void printClassName(QuartzInstanceTO quartzInstance, ObjectName objectName) throws Exception {
        if (quartzInstance == null || objectName == null) {
            return;
        }
        MBeanInfo info = quartzInstance.getMBeanServerConnection().getMBeanInfo(objectName);
        if (info != null) {
            System.out.println(info.getClassName() + " Desc: " + info.getDescription());
        }
    }

    @Override
    public SchedulerTO populateScheduler(QuartzInstanceTO quartzInstance, ObjectName objectName) throws Exception {
        if (quartzInstance == null || objectName == null) {
            return null;
        }
        final SchedulerTO scheduler = new SchedulerTO();
        final MBeanServerConnection connection = quartzInstance.getMBeanServerConnection();

        scheduler.setObjectName(objectName);
        scheduler.setName((String) connection.getAttribute(objectName, "SchedulerName"));
        scheduler.setInstanceId((String) connection.getAttribute(objectName, "SchedulerInstanceId"));
        scheduler.setJobStoreClassName((String) connection.getAttribute(objectName, "JobStoreClassName"));
        scheduler.setThreadPoolClassName((String) connection.getAttribute(objectName, "ThreadPoolClassName"));
        scheduler.setThreadPoolSize((Integer) connection.getAttribute(objectName, "ThreadPoolSize"));
        scheduler.setShutdown((Boolean) connection.getAttribute(objectName, "Shutdown"));
        scheduler.setStarted((Boolean) connection.getAttribute(objectName, "Started"));
        scheduler.setStandByMode((Boolean) connection.getAttribute(objectName, "StandbyMode"));
        scheduler.setQuartzInstanceUUID(quartzInstance.getUuid());
        scheduler.setVersion(this.getVersion(quartzInstance, objectName));
        return scheduler;
    }

    @Override
    public List<JobTO> getJobDetails(QuartzInstanceTO quartzInstance, String scheduleID) throws Exception {
        if (quartzInstance == null) {
            return Collections.emptyList();
        }
        final List<JobTO> jobs = Lists.newArrayList();
        final SchedulerTO scheduler = quartzInstance.getSchedulerByInstanceId(scheduleID);

        if (scheduler == null) {
            return Collections.emptyList();
        }
        final JMXInputTO jmxInput = new JMXInputTO(quartzInstance,
                                                   new String[]{String.class.getName()},
                                                   "AllJobDetails",
                                                   new Object[]{scheduleID},
                                                   scheduler.getObjectName());
        final TabularDataSupport tData = (TabularDataSupport) this.callJMXAttribute(jmxInput);

        if (tData == null) {
            return jobs;
        }
        int i = 0;
        for (Object object : tData.values()) {
            if (!(object instanceof CompositeDataSupport) || i >= 500) {
                continue;
            }
            final CompositeDataSupport compositeDataSupport = (CompositeDataSupport) object;
            final JobTO job = new JobTO();

            job.setQuartzInstanceId(scheduler.getQuartzInstanceUUID());
            job.setSchedulerInstanceId(scheduler.getInstanceId());
            job.setJobName((String) compositeDataSupport.get("name"));
            job.setDescription((String) compositeDataSupport.get("description"));
            job.setDurability((Boolean) compositeDataSupport.get("durability"));
            job.setShouldRecover((Boolean) compositeDataSupport.get("shouldRecover"));
            job.setGroup((String) compositeDataSupport.get("group"));
            job.setJobClass((String) compositeDataSupport.get("jobClass"));
            final List<TriggerTO> triggers = this.getTriggersForJob(quartzInstance,
                                                                    scheduleID,
                                                                    job.getJobName(),
                                                                    job.getGroup());

            if (triggers != null && !triggers.isEmpty()) {
                try {
                    final Date nextFireTime = this.getNextFireTimeForJob(triggers);

                    job.setNextFireTime(nextFireTime);
                    job.setNumTriggers(triggers.size());

                } catch (Throwable t) {
                    log.error("Error ", t);
                }
            }
            log.debug("Loaded job # " + i + job);
            jobs.add(job);
            i++;
        }
        return jobs;
    }

    @Override
    public SchedulerTO getScheduler(QuartzInstanceTO quartzInstance, String scheduleID) throws Exception {
        return quartzInstance == null ? null : quartzInstance.getSchedulerByInstanceId(scheduleID);
    }

    @Override
    public List<TriggerTO> getTriggersForJob(QuartzInstanceTO quartzInstance, String scheduleID, String jobName, String groupName) throws Exception {
        if (quartzInstance == null) {
            return Collections.emptyList();
        }
        final SchedulerTO scheduler = quartzInstance.getSchedulerByInstanceId(scheduleID);

        if (scheduler == null) {
            return Collections.emptyList();
        }
        final JMXInputTO jmxInput = new JMXInputTO(quartzInstance,
                                                   new String[]{String.class.getName(), String.class.getName()},
                                                   "getTriggersOfJob",
                                                   new Object[]{jobName, groupName},
                                                   scheduler.getObjectName());
        final List list = (List) this.callJMXOperation(jmxInput);

        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        final List<TriggerTO> triggers = new ArrayList<>();

        for (Object aList : list) {
            final CompositeDataSupport compositeDataSupport = (CompositeDataSupport) aList;
            final TriggerTO trigger = new TriggerTO();

            trigger.setCalendarName((String) compositeDataSupport.get("calendarName"));
            trigger.setDescription((String) compositeDataSupport.get("description"));
            trigger.setEndTime((Date) compositeDataSupport.get("endTime"));
            trigger.setFinalFireTime((Date) compositeDataSupport.get("finalFireTime"));
            trigger.setFireInstanceId((String) compositeDataSupport.get("fireInstanceId"));
            trigger.setGroup((String) compositeDataSupport.get("group"));
            trigger.setJobGroup((String) compositeDataSupport.get("jobGroup"));
            trigger.setJobName((String) compositeDataSupport.get("jobName"));
            trigger.setMisfireInstruction((Integer) compositeDataSupport.get("misfireInstruction"));
            trigger.setName((String) compositeDataSupport.get("name"));
            trigger.setNextFireTime((Date) compositeDataSupport.get("nextFireTime"));
            trigger.setPreviousFireTime((Date) compositeDataSupport.get("previousFireTime"));
            trigger.setPriority((Integer) compositeDataSupport.get("priority"));
            trigger.setStartTime((Date) compositeDataSupport.get("startTime"));

            try {
                final JMXInputTO stateJmxInput = new JMXInputTO(quartzInstance,
                                                                new String[]{String.class.getName(), String.class.getName()},
                                                                "getTriggerState",
                                                                new Object[]{trigger.getName(), trigger.getGroup()},
                                                                scheduler.getObjectName());
                final String state = (String) this.callJMXOperation(stateJmxInput);

                trigger.setSTriggerState(state);
            } catch (Throwable tt) {
                trigger.setSTriggerState(TriggerTO.STATE_GET_ERROR);
            }
            triggers.add(trigger);
        }
        return triggers;
    }

    private Object callJMXAttribute(JMXInputTO jmxInput) throws Exception {
        if (jmxInput == null) {
            return null;
        }
        final QuartzInstanceTO quartzInstance = jmxInput.getQuartzInstance();

        if (quartzInstance != null) {
            final MBeanServerConnection connection = quartzInstance.getMBeanServerConnection();

            if (connection != null) {
                return connection.getAttribute(jmxInput.getObjectName(), jmxInput.getOperation());
            }
        }
        return null;
    }

    private Object callJMXOperation(JMXInputTO jmxInput) throws Exception {
        if (jmxInput == null) {
            return null;
        }
        final QuartzInstanceTO quartzInstance = jmxInput.getQuartzInstance();

        if (quartzInstance != null) {
            final MBeanServerConnection connection = quartzInstance.getMBeanServerConnection();

            if (connection != null) {
                try {
                    return connection.invoke(jmxInput.getObjectName(),
                                             jmxInput.getOperation(),
                                             jmxInput.getParameters(),
                                             jmxInput.getSignature());
                } catch (Exception ignored) {
                }
            }
        }
        return null;
    }

    private Date getNextFireTimeForJob(List<TriggerTO> triggers) {
        Date theNext = null;
        if (triggers != null && triggers.size() > 0) {

            for (int i = 0; i < triggers.size(); i++) {
                TriggerTO trigger = triggers.get(i);
                if (i == 0) {
                    theNext = trigger.getNextFireTime();
                }
                if (trigger.getNextFireTime().before(theNext)) {
                    theNext = trigger.getNextFireTime();
                }
            }
        }
        return theNext;
    }
}
