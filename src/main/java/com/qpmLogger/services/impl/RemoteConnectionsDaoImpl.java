package com.qpmLogger.services.impl;

import com.qpmLogger.services.RemoteConnectionsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Satimov Murad
 * Date: 8/7/17 11:23 PM
 */
@Service
public class RemoteConnectionsDaoImpl implements RemoteConnectionsDao {

    @Autowired
    private RemoteConnectionsDao remoteConnectionsDao;
}
