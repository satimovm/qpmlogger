package com.qpmLogger.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class RemoteConnectionTO implements Serializable {

    private Long id;
    private String uname;
    private String uuid;
    private String host;
    private int port;
    private String userName;
    private String password;
    private Boolean connected;

    public RemoteConnectionTO(String uuid, String host, int port, String userName, String password) {
        this.uuid = uuid;
        this.host = host;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }
}