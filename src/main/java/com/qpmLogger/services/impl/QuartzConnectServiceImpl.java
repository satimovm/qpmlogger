package com.qpmLogger.services.impl;

import com.google.common.collect.Lists;
import com.qpmLogger.dto.QuartzConfigTO;
import com.qpmLogger.dto.QuartzInstanceTO;
import com.qpmLogger.dto.SchedulerTO;
import com.qpmLogger.listeners.JobEventReceiveListener;
import com.qpmLogger.services.QuartzConnectService;
import com.qpmLogger.services.QuartzJMXAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: satimov
 * Date: 8/8/17 2:48 PM
 */
@Slf4j
@Service
public class QuartzConnectServiceImpl implements QuartzConnectService {

    @Autowired
    private QuartzJMXAdapter quartzJMXAdapter;

    @Override
    @Transactional
    public QuartzInstanceTO initInstance(QuartzConfigTO config) throws Exception {
        if (config == null) {
            return null;
        }
        final Map<String, String[]> env = new HashMap<>();

        env.put(JMXConnector.CREDENTIALS, new String[]{config.getUserName(), config.getPassword()});
        final JMXServiceURL jmxServiceURL = this.createQuartzInstanceConnection(config);
        final JMXConnector connector = JMXConnectorFactory.connect(jmxServiceURL, env);
        final MBeanServerConnection connection = connector.getMBeanServerConnection();
        final ObjectName mBName = new ObjectName("quartz:type=QuartzScheduler,*");
        final Set<ObjectName> names = connection.queryNames(mBName, null);
        final QuartzInstanceTO quartzInstance = new QuartzInstanceTO(config);

        quartzInstance.setMBeanServerConnection(connection);
        quartzInstance.setJmxConnector(connector);
        final List<SchedulerTO> schList = Lists.newArrayListWithExpectedSize(names.size());

        for (ObjectName objectName : names) {
            final SchedulerTO scheduler = quartzJMXAdapter.populateScheduler(quartzInstance, objectName);

            schList.add(scheduler);
            final JobEventReceiveListener listener = new JobEventReceiveListener();

            listener.setUUID(scheduler.getUuidInstance());
            connection.addNotificationListener(objectName, listener, null, null);
            log.info("added listener " + objectName.getCanonicalName());
            QuartzInstanceTO.putListener(listener);
        }
        quartzInstance.setSchedulerList(schList);
        return quartzInstance;
    }

    private JMXServiceURL createQuartzInstanceConnection(QuartzConfigTO quartzConfig) throws MalformedURLException {
        if (quartzConfig == null) {
            return null;
        }
        return new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" +
                                 quartzConfig.getHost() +
                                 ":" +
                                 quartzConfig.getPort() +
                                 "/jmxrmi");
    }

}
