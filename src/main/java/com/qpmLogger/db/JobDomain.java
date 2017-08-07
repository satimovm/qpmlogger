package com.qpmLogger.db;

import com.qpmLogger.constants.TableNameConstants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * User: Satimov Murad
 * Date: 8/7/17 11:07 PM
 */
@Getter
@Setter
@Entity
@Table(name = TableNameConstants.Job)
public class JobDomain implements Serializable {
    @Id
    @Column(nullable = false)
    private Long id;


}
