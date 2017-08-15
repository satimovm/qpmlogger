package com.qpmLogger.services.impl;

import com.qpmLogger.datasource.mongo.dao.JobMongoDaoCustom;
import com.qpmLogger.datasource.postgres.dao.ExecutedJobEventDao;
import com.qpmLogger.datasource.postgres.dao.JobEventDao;
import com.qpmLogger.datasource.postgres.db.ExecutedJobEventDomain;
import com.qpmLogger.datasource.postgres.db.JobEventDomain;
import com.qpmLogger.dto.JobEventTO;
import com.qpmLogger.services.JobEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Satimov Murad
 * Date: 8/7/17 11:29 PM
 */
@Slf4j
@Service("jobEventService")
public class JobEventServiceImpl implements JobEventService {

    @Autowired
    private JobEventDao jobEventDao;
    @Autowired
    private ExecutedJobEventDao executedJobEventDao;
    @Autowired
    private JobMongoDaoCustom jobMongoDao;

    @Async
    @Override
    @Transactional
    public void saveExecutedEvent(JobEventTO event) {
        log.info(event.toString());
        final ExecutedJobEventDomain domain = new ExecutedJobEventDomain().fromTO(event);

        if (domain == null) {
            log.error("Empty job event");
            return;
        }
        executedJobEventDao.save(domain);
        jobMongoDao.save(domain.toMongoEntity());
    }

    @Async
    @Override
    @Transactional
    public void saveJobEvent(JobEventTO event) {
        final JobEventDomain domain = new JobEventDomain().fromTO(event);

        if (domain == null) {
            log.error("Empty job event");
            return;
        }
        jobEventDao.save(domain);
    }
}
