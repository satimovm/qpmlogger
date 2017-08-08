package com.qpmLogger.listeners;

import com.qpmLogger.dto.QuartzInstanceTO;
import com.qpmLogger.services.QuartzConnectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.management.remote.JMXConnector;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Map;

/**
 * User: satimov
 * Date: 8/8/17 4:57 PM
 */
@Slf4j
@Component
public class DestroyDisposableBean implements DisposableBean {

    @Autowired
    private QuartzConnectService connectService;

    @Override
    @Transactional
    public void destroy() throws Exception {
        log.info("Shutting down SettingsLoaderListener service...");
        final Map<String, QuartzInstanceTO> instanceMap = connectService.getInstanceMap();

        for (Map.Entry<String, QuartzInstanceTO> entry : instanceMap.entrySet()) {
            final String key = entry.getKey();
            final QuartzInstanceTO instanceTO = entry.getValue();

            try {
                final JMXConnector jmxConnector = instanceTO.getJmxConnector();

                if (jmxConnector != null) {
                    jmxConnector.close();
                }
            } catch (IOException e) {
                log.error("Failed to close Connection: " + instanceTO, e);
            }
        }
        final Enumeration<Driver> drivers = DriverManager.getDrivers();

        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                log.info(String.format("deregister jdbc driver: %s", driver));
            } catch (SQLException e) {
                log.error(String.format("Error deregister driver %s", driver), e);
            }
        }
    }
}
