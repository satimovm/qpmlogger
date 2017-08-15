package com.qpmLogger;

import com.qpmLogger.services.QuartzConnectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@Slf4j
@SpringBootApplication(scanBasePackages = {
        "com.qpmLogger.configuration",
        "com.qpmLogger.datasource.postgres.dao",
        "com.qpmLogger.listeners",
        "com.qpmLogger.services",
        "com.qpmLogger.datasource.mongo"
})
public class QpmloggerApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    private QuartzConnectService connectService;

    public static void main(String[] args) {
        SpringApplication.run(QpmloggerApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("Start Init connections");
        connectService.initQuartzInstanceMap();
        System.out.println("END Init connections");
        log.info("------------------------------------------------------------------------------");
        log.info("------------------------------------------------------------------------------");
        log.info("------------------------------------------------------------------------------");
    }
}