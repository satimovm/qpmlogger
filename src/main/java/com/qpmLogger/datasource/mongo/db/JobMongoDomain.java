package com.qpmLogger.datasource.mongo.db;

import com.qpmLogger.constants.TableNameConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * User: Satimov Murad
 * Date: 8/10/17 4:11 PM
 */
@Getter
@Setter
@Document(collection = TableNameConstants.ExecutedJobEvent)
public class JobMongoDomain extends BaseMongoDomain {
    private String calendarName;
    private String jobGroup;
    private String jobName;
    private String schedulerName;
    private String triggerGroup;
    private String triggerName;
    private Date fireTime;
    private Date nextFireTime;
    private Date previousFireTime;
    private Date scheduledFireTime;
    private boolean recovering;
    private long jobRunTime;
    private int refireCount;
    private String schedulerId;
    private String quartzInstanceId;
    private String type;

    @Override
    public String getKey() {
        return TableNameConstants.ExecutedJobEvent;
    }
}
