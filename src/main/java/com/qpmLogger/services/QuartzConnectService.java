package com.qpmLogger.services;

import com.qpmLogger.dto.RemoteConnectionTO;
import com.qpmLogger.dto.QuartzInstanceTO;

/**
 * User: Murad Satimov
 * Date: 8/8/17 2:48 PM
 */
public interface QuartzConnectService {
    QuartzInstanceTO initInstance(RemoteConnectionTO config) throws Exception;
}
