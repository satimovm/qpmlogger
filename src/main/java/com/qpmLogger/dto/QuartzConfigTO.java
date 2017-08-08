package com.qpmLogger.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * User: satimov
 * Date: 8/7/17 11:43 PM
 */
@Getter
@Setter
@ToString
public class QuartzConfigTO implements Serializable {
    private String uname;
    private String uuid;
    private String host;
    private int port;
    private String userName;
    private String password;
    private boolean isConnected;

    public QuartzConfigTO(String uuid, String host, int port, String userName, String password){
        this.uuid = uuid;
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }
}