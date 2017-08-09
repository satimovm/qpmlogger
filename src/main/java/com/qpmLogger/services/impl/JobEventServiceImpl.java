package com.qpmLogger.services.impl;

import com.qpmLogger.dao.ExecutedJobEventDao;
import com.qpmLogger.dao.JobEventDao;
import com.qpmLogger.db.ExecutedJobEventDomain;
import com.qpmLogger.db.JobEventDomain;
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
    private ExecutedJobEventDao executedJobEventDao;
    @Autowired
    private JobEventDao jobEventDao;

    @Async
    @Override
    @Transactional
    public void saveExecutedEvent(JobEventTO event) {
        log.info("Saving new executed JobEvent: " + event);
        final ExecutedJobEventDomain domain = new ExecutedJobEventDomain().fromTO(event);

        if (domain == null) {
            log.error("Empty job event");
            return;
        }
        executedJobEventDao.save(domain);
    }

    @Async
    @Override
    @Transactional
    public void saveJobEvent(JobEventTO event) {
        log.info("Saving new JobEvent: " + event);
        final JobEventDomain domain = new JobEventDomain().fromTO(event);

        if (domain == null) {
            log.error("Empty job event");
            return;
        }
        jobEventDao.save(domain);
    }
}
