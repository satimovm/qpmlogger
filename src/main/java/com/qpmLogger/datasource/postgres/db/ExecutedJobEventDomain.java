package com.qpmLogger.datasource.postgres.db;

import com.qpmLogger.constants.TableNameConstants;
import com.qpmLogger.dto.JobEventTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Satimov Murad
 * Date: 8/7/17 11:07 PM
 */
@Getter
@Setter
@Entity
@Table(name = TableNameConstants.ExecutedJobEvent)
public class ExecutedJobEventDomain extends BaseJobEventDomain {

    @Override
    public ExecutedJobEventDomain fromTO(JobEventTO item) {
        return (ExecutedJobEventDomain) super.fromTO(item);
    }
}
