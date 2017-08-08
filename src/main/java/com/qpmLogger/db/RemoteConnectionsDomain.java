package com.qpmLogger.db;

import com.google.common.base.Optional;
import com.qpmLogger.constants.TableNameConstants;
import com.qpmLogger.dto.RemoteConnectionTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdDate;
    private Date modifiedDate;
    private boolean deleted;
    private String name;
    private String host;
    private Integer port;
    private String uuid;

    private String userName;
    private String password;
    private Boolean connected;
    private Integer tryCount;

    @PrePersist
    public void prePersist() {
        this.setCreatedDate(new Date());
        this.setModifiedDate(new Date());
    }

    @PreUpdate
    public void preUpdate() {
        this.setModifiedDate(new Date());
    }

    public RemoteConnectionTO toTO() {
        final RemoteConnectionTO item = new RemoteConnectionTO();

        item.setId(this.getId());
        item.setHost(this.getHost());
        item.setPort(this.getPort());
        item.setUserName(this.getUserName());
        item.setPassword(this.getPassword());
        item.setConnected(Optional.fromNullable(this.getConnected())
                                  .or(false));
        item.setUuid(this.getUuid());
        return item;
    }

    public void fromTO(RemoteConnectionTO item) {
        if (item == null) {
            return;
        }
        this.setId(item.getId());
        this.setHost(item.getHost());
        this.setPort(item.getPort());
        this.setUserName(item.getUserName());
        this.setPassword(item.getPassword());
        this.setConnected(item.getConnected());
        this.setUuid(item.getUuid());
    }
}
