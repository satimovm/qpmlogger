package com.qpmLogger.dao;

import com.qpmLogger.db.JobDomain;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Satimov
 * Date: 8/7/17 11:13 PM
 */
public interface JobDao extends JpaRepository<JobDomain, Long> {
}
