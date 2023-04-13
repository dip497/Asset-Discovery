package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.entity.Monitor;
import com.serviceops.assetdiscovery.rest.MonitorRest;
import com.serviceops.assetdiscovery.service.interfaces.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{refId}/monitor")
public class MonitorServiceController {

    private final MonitorService monitorService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public MonitorServiceController(MonitorService monitorService){
        this.monitorService=monitorService;
    }
    @GetMapping()
    public List<MonitorRest> getMonitor(@PathVariable("refId") Long refId){

        logger.debug("Fetching ComputerSystem with Asset id -> {}",refId);

        List<MonitorRest> monitors = monitorService.getMonitors(refId);

        return monitors;

    }

    @DeleteMapping("/{id}")
    public void deleteMonitor(@PathVariable("id") Long id,@PathVariable("refId") Long refId){

        logger.debug("Deleting Monitor with Asset id -> {}",refId);

        monitorService.deleteById(refId);
    }

    @PostMapping()
    public void addMonitor(@PathVariable("refId") Long refId, @RequestBody MonitorRest monitorRest){

        logger.debug("Updating updateComputerSystem with Asset id -> {}",refId);

        monitorService.update(monitorRest);
    }

}
