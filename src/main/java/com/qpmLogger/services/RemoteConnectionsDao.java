package com.qpmLogger.services;

import com.qpmLogger.db.RemoteConnectionsDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: Satimov Murad
 * Date: 8/7/17 11:23 PM
 */
public interface RemoteConnectionsDao extends JpaRepository<RemoteConnectionsDomain, Long> {

    List<RemoteConnectionsDomain> findAllByDeletedFalse();
}
