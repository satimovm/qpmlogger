package com.qpmLogger.datasource.mongo.dao;

import com.qpmLogger.exceptions.SequenceException;

/**
 * User: Satimov Murad
 * Date: 8/10/17 5:41 PM
 */
public interface SequenceDao {

    Long getNextSequenceId(String key) throws SequenceException;

}
