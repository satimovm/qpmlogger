package com.qpmLogger.listeners;

import com.qpmLogger.services.QuartzConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * User: Satimov Murad
 * Date: 8/8/17 5:26 PM
 */
@Component
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private QuartzConnectService connectService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("............................Starting application............................");
        System.out.println("............................Starting application............................");
        System.out.println("............................Start Init application..........................");
        connectService.initQuartzInstanceMap();
    }
}
