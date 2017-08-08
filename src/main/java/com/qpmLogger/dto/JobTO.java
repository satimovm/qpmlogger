package com.qpmLogger.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * User: Satimov Murad
 * Date: 8/8/17 3:08 PM
 */
@Getter
@Setter
public class JobTO implements Serializable {
    private String quartzInstanceId;
    private String schedulerInstanceId;
    private String description;
    private boolean durability;
    private String group;
    private String jobClass;
    private String jobName;
    private boolean shouldRecover;
    private Map jobDataMap;
    private Date nextFireTime;
    private int numTriggers;
}
