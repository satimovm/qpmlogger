package com.qpmLogger.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * User: Satimov Murad
 * Date: 8/8/17 3:22 PM
 */
@Getter
@Setter
public class TriggerTO implements Serializable {
    public static final String STATE_GET_ERROR = "Q_ERROR";
    private String calendarName;
    private String description;
    private Date endTime;
    private Date finalFireTime;
    private String fireInstanceId;
    private String group;
    private String jobGroup;
    private String jobName;
    private int misfireInstruction;
    private String name;
    private Date nextFireTime;
    private Date previousFireTime;
    private int priority;
    private Date startTime;
    private String sTriggerState;
}