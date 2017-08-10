package com.qpmLogger.datasource.mongo.dao.impl;

import com.qpmLogger.datasource.mongo.dao.SequenceDao;
import com.qpmLogger.datasource.mongo.db.SequenceId;
import com.qpmLogger.exceptions.SequenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * User: satimov
 * Date: 8/10/17 5:42 PM
 */
@Repository
public class SequenceDaoImpl implements SequenceDao {

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public Long getNextSequenceId(String key) throws SequenceException {
        final Query query = new Query(Criteria.where("_id").is(key));

        final Update update = new Update();
        update.inc("seq", 1);
        final FindAndModifyOptions options = new FindAndModifyOptions();

        options.returnNew(true);
        final SequenceId seqId = mongoOperation.findAndModify(query, update, options, SequenceId.class);

        if (seqId == null) {
            throw new SequenceException("Unable to get sequence id for key : " + key);
        }
        return seqId.getSeq();
    }
}
