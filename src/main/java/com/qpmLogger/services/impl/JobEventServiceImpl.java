package com.qpmLogger.services.impl;

import com.qpmLogger.dao.JobEventDao;
import com.qpmLogger.db.JobEventDomain;
import com.qpmLogger.dto.JobEventTO;
import com.qpmLogger.services.JobEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Satimov Murad
 * Date: 8/7/17 11:29 PM
 */
@Slf4j
@Service
public class JobEventServiceImpl implements JobEventService {

    private final JobEventDao jobEventDao;

    @Autowired
    public JobEventServiceImpl(JobEventDao jobEventDao) {
        this.jobEventDao = jobEventDao;
    }

    @Override
    @Transactional
    public void saveNewEvent(JobEventTO event) {
        if (event == null) {
            throw new RuntimeException("Empty event");
        }
        log.info("Saving new JobEvent: " + event);
        final JobEventDomain eventDomain = new JobEventDomain();

        eventDomain.fromTO(event);
        jobEventDao.save(eventDomain);
    }
}
