package com.qpmLogger.services.impl;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qpmLogger.db.RemoteConnectionsDomain;
import com.qpmLogger.dto.QuartzInstanceTO;
import com.qpmLogger.dto.RemoteConnectionTO;
import com.qpmLogger.dto.SchedulerTO;
import com.qpmLogger.listeners.JobEventReceiveListener;
import com.qpmLogger.services.QuartzConnectService;
import com.qpmLogger.services.QuartzJMXAdapter;
import com.qpmLogger.services.RemoteConnectionsDao;
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

    private static Map<String, QuartzInstanceTO> quartzInstanceMap = Maps.newConcurrentMap();
    @Autowired
    private QuartzJMXAdapter quartzJMXAdapter;
    @Autowired
    private RemoteConnectionsDao remoteConnectionsDao;

    @Override
    @Transactional
    public QuartzInstanceTO initInstance(RemoteConnectionTO config) throws Exception {
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

    @Override
    public Map<String, QuartzInstanceTO> getInstanceMap() {
        return quartzInstanceMap;
    }

    @Override
    public void putInstanceMap(QuartzInstanceTO quartzInstanceTO) {
        if (quartzInstanceTO == null) {
            return;
        }
        if (quartzInstanceMap == null) {
            quartzInstanceMap = Maps.newConcurrentMap();
        }
        quartzInstanceMap.put(quartzInstanceTO.getUuid(), quartzInstanceTO);

    }

    @Override
    @Transactional
    public void initQuartzInstanceMap() {
        final List<RemoteConnectionsDomain> configs = remoteConnectionsDao.findAllByDeletedFalseAndTryCountIsLessThanEqual(5);

        log.info("Found " + configs.size() + " Quartz instances in settings file.");

        for (RemoteConnectionsDomain config : configs) {
            QuartzInstanceTO quartzInstance = null;
            try {
                quartzInstance = this.initInstance(config.toTO());
                config.setConnected(true);
                config.setConnected(true);
            } catch (Throwable t) {
                log.error("Failed to connect! " + config.toString(), t);
                config.setConnected(false);
                config.setTryCount(Optional.fromNullable(config.getTryCount()).or(0) + 1);
            }
            if (quartzInstance != null) {
                this.putInstanceMap(quartzInstance);
                log.debug(quartzInstance.toString());
            }
        }
    }

    private JMXServiceURL createQuartzInstanceConnection(RemoteConnectionTO quartzConfig) throws MalformedURLException {
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