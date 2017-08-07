package com.qpmLogger.dao;

import com.qpmLogger.db.RemoteConnectionsDomain;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: satimov
 * Date: 8/7/17 11:22 PM
 */
public interface RemoteConnectionsDao extends JpaRepository<RemoteConnectionsDomain, Long> {
}
