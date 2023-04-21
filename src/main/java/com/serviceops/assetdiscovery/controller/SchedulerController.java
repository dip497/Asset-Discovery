package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.SchedulerRest;
import com.serviceops.assetdiscovery.service.interfaces.SchedulersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "/networkScan/{networkScanId}/addScheduler", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addScheduler(@PathVariable long networkScanId, @RequestBody SchedulerRest schedulerRest) {
        logger.debug("saving scheduler for networkscan with id -> {}", networkScanId);
        schedulersService.save(networkScanId, schedulerRest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/networkScan/{networkScanId}/addScheduler/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addScheduler(@PathVariable long networkScanId, @PathVariable long id,
            @RequestBody SchedulerRest schedulerRest) {
        logger.debug("saving scheduler for networkscan with id -> {}", networkScanId);
        schedulersService.update(networkScanId, id, schedulerRest);
    }

    @GetMapping(value = "/networkScan/{networkScanId}/addScheduler", produces = MediaType.APPLICATION_JSON_VALUE)
    public SchedulerRest getScheduler(@PathVariable long networkScanId) {
        logger.debug("fetching scheduler for networkscan with id -> {}", networkScanId);
        return schedulersService.findByNetworkScanId(networkScanId);
    }


}
