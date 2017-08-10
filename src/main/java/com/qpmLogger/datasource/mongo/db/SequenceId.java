package com.qpmLogger.datasource.mongo.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User: satimov
 * Date: 8/10/17 5:40 PM
 */
@Getter
@Setter
@ToString
@Document(collection = "sequence")
public class SequenceId {
    @Id
    private String id;
    private long seq;
}