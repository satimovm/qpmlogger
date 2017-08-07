package com.qpmLogger.controllers;

import com.qpmLogger.constants.MappingConstants;
import com.qpmLogger.dto.BaseResponseTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Murad
 * Date: 8/7/17 10:04 PM
 */
@RestController
@RequestMapping(MappingConstants.Jobs)
public class JobApi extends BaseApi {

    @RequestMapping(
            value = List,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponseTO> getJobList(@RequestParam(required = false) int limit) {
        System.out.println(limit);
        return ResponseEntity.ok(new BaseResponseTO());
    }
}
