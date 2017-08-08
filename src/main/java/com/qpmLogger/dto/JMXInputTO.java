package com.qpmLogger.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.ObjectName;
import java.io.Serializable;

/**
 * User: satimov
 * Date: 8/8/17 3:39 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JMXInputTO implements Serializable {
    private QuartzInstanceTO quartzInstance;
    private String[] signature;
    private String operation;
    private Object[] parameters;
    private ObjectName objectName;
}
