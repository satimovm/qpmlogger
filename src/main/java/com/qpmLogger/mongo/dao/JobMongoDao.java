package com.qpmLogger.mongo.dao;

import com.qpmLogger.mongo.db.JobMongoDomain;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * User: Murad Satimov
 * Date: 8/10/17 4:13 PM
 */
public interface JobMongoDao extends MongoRepository<JobMongoDomain, Long> {
}
