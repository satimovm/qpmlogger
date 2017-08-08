package com.qpmLogger.dto;

import com.qpmLogger.listeners.JobEventReceiveListener;
import com.qpmLogger.services.QuartzJMXAdapter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Satimov Murad
 * Date: 8/8/17 2:50 PM
 */
@Getter
@Setter
@ToString
public class QuartzInstanceTO extends RemoteConnectionTO {
    private static Map<String, JobEventReceiveListener> listenersMap = new HashMap<>();
    private MBeanServerConnection mBeanServerConnection;
    private QuartzJMXAdapter jmxAdapter;
    private List<SchedulerTO> schedulerList = new ArrayList<>();
    private JMXConnector jmxConnector;

    public QuartzInstanceTO(RemoteConnectionTO config) {
        super(config.getUuid(), config.getHost(), config.getPort(), config.getUserName(), config.getPassword());
    }

    public static void putListener(JobEventReceiveListener listener) {
        if (listener != null) {
            if (listenersMap == null) {
                listenersMap = new HashMap<>();
            }
            listenersMap.put(listener.getUUID(), listener);
        }
    }

    public SchedulerTO getSchedulerByInstanceId(String instanceId) {
        for (SchedulerTO scheduler : this.getSchedulerList()) {
            if (scheduler != null && scheduler.getInstanceId().equals(instanceId)) {
                return scheduler;
            }
        }
        return null;
    }

}
