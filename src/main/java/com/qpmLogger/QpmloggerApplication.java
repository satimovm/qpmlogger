package com.qpmLogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackages = {
        "com.qpmLogger.configuration",
        "com.qpmLogger.dao",
        "com.qpmLogger.listeners",
        "com.qpmLogger.services",
        "com.qpmLogger.mongo"
})
public class QpmloggerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(QpmloggerApplication.class, args);
    }
}