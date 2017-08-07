package com.qpmLogger.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * User: satimov
 * Date: 8/7/17 10:06 PM
 */
@Data
public class BaseResponseTO<T> implements Serializable {
    private String message;
    private boolean success;
    private T object;
}
