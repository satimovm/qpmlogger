package com.qpmLogger.services;

import com.qpmLogger.dto.JobEventTO;

/**
 * User: Satimov Murad
 * Date: 8/7/17 11:29 PM
 */
public interface JobEventService {

    void saveNewExecutedEvent(JobEventTO event);

    void saveOtherExecutedEvent(JobEventTO event);
}
