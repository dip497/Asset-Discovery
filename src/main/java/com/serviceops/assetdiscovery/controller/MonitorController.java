package com.serviceops.assetdiscovery.controller;


import com.serviceops.assetdiscovery.rest.MonitorRest;
import com.serviceops.assetdiscovery.service.interfaces.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MonitorController {

    private static final Logger logger = LoggerFactory.getLogger(MonitorController.class);
    private final MonitorService monitorService;

    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @GetMapping(value = "/{refId}/monitor", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitorRest> getMonitors(@PathVariable("refId") long refId) {

        logger.info("Fetching Monitor with Asset id -> {}", refId);
        List<MonitorRest> monitors = monitorService.findAllByRefId(refId);

        return monitors;

    }

    @PutMapping(value = "/{refId}/monitor/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateMonitor(@PathVariable("refId") long refId, @PathVariable("id") long id,
            @RequestBody MonitorRest monitorRest) {

        logger.debug("Updating monitor with Asset id -> {}", refId);

        monitorService.updateById(refId, id, monitorRest);
    }

    @DeleteMapping("/{refId}/monitor/{id}")
    public boolean deleteMonitor(@PathVariable("refId") long refId, @PathVariable("id") long id) {

        logger.info("Deleting Monitor with Asset id -> {}", refId);
        return monitorService.deleteById(refId, id);
    }

}
