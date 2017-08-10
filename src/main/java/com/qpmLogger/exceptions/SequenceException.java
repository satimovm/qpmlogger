package com.qpmLogger.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * User: Satimov Murad
 * Date: 8/10/17 5:43 PM
 */
@Getter
@Setter
public class SequenceException extends RuntimeException {
    private String errCode;
    private String errMsg;

    public SequenceException(String errMsg) {
        this.errMsg = errMsg;
    }
}
