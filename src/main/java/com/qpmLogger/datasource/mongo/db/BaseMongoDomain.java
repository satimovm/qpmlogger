package com.qpmLogger.datasource.mongo.db;

import com.qpmLogger.datasource.HasKey;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * User: Satimov Murad
 * Date: 8/10/17 5:45 PM
 */
@Getter
@Setter
public abstract class BaseMongoDomain implements Serializable, Cloneable, HasKey {
    @Id
    private Long id;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date modifiedDate;
}
