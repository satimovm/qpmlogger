package com.qpmLogger.db;

import com.qpmLogger.constants.TableNameConstants;
import com.qpmLogger.dto.JobEventTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * User: Satimov Murad
 * Date: 8/7/17 11:07 PM
 */
@Getter
@Setter
@Entity
@Table(name = TableNameConstants.JobEvent)
public class JobEventDomain extends BaseJobEventDomain implements Serializable {


    @Override
    public JobEventDomain fromTO(JobEventTO item) {
        return (JobEventDomain) super.fromTO(item);
    }
}
