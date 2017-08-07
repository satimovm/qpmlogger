package com.qpmLogger.services.impl;

import com.qpmLogger.dao.JobDao;
import com.qpmLogger.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: satimov
 * Date: 8/7/17 11:17 PM
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;
}
