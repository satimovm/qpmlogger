package com.qpmLogger.controllers;

import com.qpmLogger.dto.BaseResponseTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Murad
 * Date: 8/7/17 10:04 PM
 */
@RestController
@RequestMapping("/web-api/jobs")
public class JobMonitorApi {

    @RequestMapping()
    public ResponseEntity<BaseResponseTO> getJobList(){
        return ResponseEntity.ok(new BaseResponseTO());
    }
}
