package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.SchedulerRest;
import com.serviceops.assetdiscovery.service.interfaces.SchedulersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchedulerController {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerController.class);
    private final SchedulersService schedulersService;

    public SchedulerController(SchedulersService schedulersService) {
        this.schedulersService = schedulersService;
    }

    @GetMapping(value = "/networkScan/{networkScanId}/scheduler", produces = MediaType.APPLICATION_JSON_VALUE)
    public SchedulerRest getScheduler(@PathVariable long networkScanId) {
        logger.debug("fetching scheduler for networkscan with id -> {}", networkScanId);
        return schedulersService.findByNetworkScanId(networkScanId);
    }

    @PostMapping(value = "/networkScan/{networkScanId}/scheduler", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SchedulerRest> addScheduler(@PathVariable long networkScanId,
            @RequestBody SchedulerRest schedulerRest) {
        logger.debug("saving scheduler for networkscan with id -> {}", networkScanId);
        return new ResponseEntity<>(schedulersService.save(networkScanId, schedulerRest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/networkScan/{networkScanId}/scheduler", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SchedulerRest updateScheduler(@PathVariable long networkScanId,
            @RequestBody SchedulerRest schedulerRest) {
        logger.debug("updating scheduler for networkscan with id -> {}", networkScanId);
        return schedulersService.update(networkScanId, schedulerRest);
    }

    @DeleteMapping(value = "/networkScan/{networkScanId}/scheduler")
    public boolean deleteScheduler(@PathVariable long networkScanId){
        logger.debug("deleting scheduler for networkscan with id -> {}", networkScanId);
        return schedulersService.deleteByNetworkScanId(networkScanId);
    }


}
