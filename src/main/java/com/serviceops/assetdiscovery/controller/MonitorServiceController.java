package com.serviceops.assetdiscovery.controller;


import com.serviceops.assetdiscovery.rest.MonitorRest;
import com.serviceops.assetdiscovery.service.interfaces.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MonitorServiceController {

    private final MonitorService monitorService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public MonitorServiceController(MonitorService monitorService){
        this.monitorService=monitorService;
    }
    @GetMapping(value = "/{refId}/monitor",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitorRest> getMonitor(@PathVariable("refId") Long refId){

        logger.info("Fetching Monitor with Asset id -> {}",refId);
        List<MonitorRest> monitors = monitorService.getMonitors(refId);

        return monitors;

    }

    @DeleteMapping("/{refId}/monitor/{id}")
    public void deleteMonitor(@PathVariable("refId") Long refId,@PathVariable("id") Long id){

        logger.info("Deleting Monitor with Asset id -> {}",refId);
        monitorService.deleteById(refId,id);
    }

    @PutMapping(value = "/{refId}/monitor/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateMonitor(@PathVariable("refId") Long refId,@PathVariable("id") Long id, @RequestBody MonitorRest monitorRest){

        logger.debug("Updating monitor with Asset id -> {}",refId);

        monitorService.update(refId, id, monitorRest);
    }

}
