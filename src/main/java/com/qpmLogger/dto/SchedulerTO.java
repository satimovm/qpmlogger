package com.qpmLogger.dto;

import lombok.Getter;
import lombok.Setter;

import javax.management.ObjectName;
import java.io.Serializable;

/**
 * User: Satimov Murad
 * Date: 8/8/17 2:54 PM
 */
@Getter
@Setter
public class SchedulerTO implements Serializable {
    private String name;
    private String version;
    private boolean started;
    private boolean shutdown;
    private String instanceId;
    private int threadPoolSize;
    private boolean standByMode;
    private ObjectName objectName;
    private String jobStoreClassName;
    private String quartzInstanceUUID;
    private String threadPoolClassName;

    public String getUuidInstance() {
        return this.getQuartzInstanceUUID() + "@@" + this.getInstanceId();
    }
}