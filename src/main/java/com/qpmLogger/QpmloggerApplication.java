package com.qpmLogger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@Slf4j
@SpringBootApplication
public class QpmloggerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(QpmloggerApplication.class, args);
        log.info("*****************************************************************");
        log.info("*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*");
        log.info("*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*");
        log.info("*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*");
        log.info("***********************Starting tomcat***************************");
        log.info("*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*");
        log.info("*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*");
        log.info("*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*");
        log.info("*****************************************************************");
    }
}
