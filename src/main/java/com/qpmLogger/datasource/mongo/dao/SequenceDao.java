package com.qpmLogger.datasource.mongo.dao;

/**
 * User: Satimov Murad
 * Date: 8/10/17 5:41 PM
 */
public interface SequenceDao {

    long getNextSequenceId(String key) throws SequenceException;

}
