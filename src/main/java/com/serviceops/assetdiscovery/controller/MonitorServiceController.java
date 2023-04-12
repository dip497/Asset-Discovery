package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.entity.Monitor;
import com.serviceops.assetdiscovery.rest.MonitorRest;
import com.serviceops.assetdiscovery.service.interfaces.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/asset/{refId}/monitor")
public class MonitorServiceController {

    private final MonitorService monitorService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public MonitorServiceController(MonitorService monitorService){
        this.monitorService=monitorService;
    }
    @GetMapping("/")
    public List<MonitorRest> getMonitor(@PathVariable("refId") Long id){
        logger.debug("Request hits the get api of monitor");
        List<MonitorRest> monitors = monitorService.get(id);
        return monitors;
    }

    @DeleteMapping("/{id}")
    public void deleteMonitor(@PathVariable("id") Long id){
        logger.debug("Request hits the delete api of monitor");
        monitorService.deleteById(id);
    }

    @PostMapping("/")
    public void addMonitor(@RequestBody MonitorRest monitorRest){
        logger.debug("Request hits the add api of monitor");
        monitorService.update(monitorRest);
    }

}
