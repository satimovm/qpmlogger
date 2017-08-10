package com.qpmLogger.datasource.mongo.dao;

import com.qpmLogger.datasource.mongo.db.JobMongoDomain;
import com.qpmLogger.datasource.mongo.db.SequenceId;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * User: Murad Satimov
 * Date: 8/10/17 4:13 PM
 */
public interface JobMongoDao extends MongoRepository<JobMongoDomain, Long> {

    @Override
    default JobMongoDomain save(JobMongoDomain domain) {
        if (domain == null) {
            return domain;
        }

        return domain;
    }

    default Long getNextSequenceId(String key) {
        final Query query = new Query(Criteria.where("_id").is(key));

        Update update = new Update();
        update.inc("seq", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        final SequenceId seqId = findAndModify(query, update, options, SequenceId.class);

        return seqId.getSeq();

    }

}
