package com.qpmLogger.datasource.postgres.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * User: satimov
 * Date: 8/9/17 2:00 PM
 */
@Getter
@Setter
@MappedSuperclass
class BaseDomain implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdDate;
    private Date modifiedDate;
    private boolean deleted;

    @PrePersist
    public void prePersist() {
        this.setCreatedDate(new Date());
        this.setModifiedDate(new Date());
    }

    @PreUpdate
    public void preUpdate() {
        this.setModifiedDate(new Date());
    }
}
