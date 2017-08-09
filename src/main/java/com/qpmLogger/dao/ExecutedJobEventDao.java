package com.qpmLogger.dao;

import com.qpmLogger.db.ExecutedJobEventDomain;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Satimov Murad
 * Date: 8/7/17 11:30 PM
 */
public interface ExecutedJobEventDao extends JpaRepository<ExecutedJobEventDomain, Long> {
}
