package com.qpmLogger.services;

import com.qpmLogger.dto.QuartzInstanceTO;
import com.qpmLogger.dto.RemoteConnectionTO;

import java.util.Map;

/**
 * User: Murad Satimov
 * Date: 8/8/17 2:48 PM
 */
public interface QuartzConnectService {
    Map<String, QuartzInstanceTO> getInstanceMap();

    void putInstanceMap(QuartzInstanceTO quartzInstanceTO);

    QuartzInstanceTO initInstance(RemoteConnectionTO config) throws Exception;

    void initQuartzInstanceMap();
}
