package com.qpmLogger.datasource.postgres.dao;

import com.qpmLogger.datasource.postgres.db.JobEventDomain;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Satimov Murad
 * Date: 8/7/17 11:30 PM
 */
public interface JobEventDao extends JpaRepository<JobEventDomain, Long> {
}
