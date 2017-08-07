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
 * User: satimov
 * Date: 8/7/17 11:21 PM
 */
@Getter
@Setter
@Entity
@Table(name = TableNameConstants.Connections)
public class RemoteConnectionsDomain implements Serializable {
    @Id
    @Column(nullable = false)
    private Long id;

    private String name;
    private String host;
    private Integer port;
}
