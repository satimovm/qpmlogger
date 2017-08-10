package com.qpmLogger.datasource.mongo.dao.impl;

import com.qpmLogger.datasource.mongo.dao.JobMongoDaoCustom;
import com.qpmLogger.datasource.mongo.dao.SequenceDao;
import com.qpmLogger.datasource.mongo.db.JobMongoDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

/**
 * User: satimov
 * Date: 8/10/17 7:22 PM
 */
@Repository
public class JobMongoDaoImpl implements JobMongoDaoCustom {

    @Autowired
    private SequenceDao sequenceDao;
    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public JobMongoDomain save(JobMongoDomain domain) {
        if (domain == null) {
            return domain;
        }
        if (domain.getId() == null) {
            domain.setId(sequenceDao.getNextSequenceId(domain.getKey()));
        }
        mongoOperation.save(domain);
        return domain;
    }

}
