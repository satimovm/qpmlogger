package com.qpmLogger.datasource.mongo.dao;

import com.qpmLogger.datasource.mongo.db.JobMongoDomain;

/**
 * User: Satimov Murad
 * Date: 8/10/17 7:21 PM
 */
public interface JobMongoDaoCustom {

    JobMongoDomain save(JobMongoDomain domain);
}
