package com.qpmLogger.listeners;

import com.qpmLogger.dto.QuartzInstanceTO;
import com.qpmLogger.services.QuartzConnectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import javax.management.remote.JMXConnector;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Map;

/**
 * User: Satimov Murad
 * Date: 8/8/17 5:26 PM
 */
@Slf4j
@Component
public class ApplicationShutdownListener implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    private QuartzConnectService connectService;

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        log.info("Shutting down SettingsLoaderListener service...");
        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        final Map<String, QuartzInstanceTO> instanceMap = connectService.getInstanceMap();

        for (Map.Entry<String, QuartzInstanceTO> entry : instanceMap.entrySet()) {
            final String key = entry.getKey();
            final QuartzInstanceTO instanceTO = entry.getValue();

            try {
                final JMXConnector jmxConnector = instanceTO.getJmxConnector();

                if (jmxConnector != null) {
                    log.info("--------------Closing JMX connection----------------");
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