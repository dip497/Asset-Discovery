package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.LogicalDiskRest;
import com.serviceops.assetdiscovery.service.interfaces.LogicalDiskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{refId}/logicalDisk")
public class LogicalDiskController {

    private final LogicalDiskService logicalDiskService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public LogicalDiskController(LogicalDiskService logicalDiskService){

        this.logicalDiskService = logicalDiskService;

    }
    @GetMapping()
    public List<LogicalDiskRest> getLogicalDisks(@PathVariable("refId") Long refId){

        logger.debug("Fetching LogicalDisks with Asset id -> {}",refId);

        List<LogicalDiskRest> logicalDiskRests = logicalDiskService.getAllLogicalDisks(refId);

        return logicalDiskRests;

    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("refId") Long refId,@PathVariable("id") Long id){

        logger.debug("deleting LogicalDisks with Asset id -> {}",refId);

        logicalDiskService.deleteById(id);

    }
    @PutMapping()
    public void updateLogicalDiskById(@PathVariable("refId") Long refId,@RequestBody LogicalDiskRest logicalDiskRest){

        logger.debug("updating LogicalDisks with Asset id -> {}",refId);

        logicalDiskService.update(logicalDiskRest);
    }

}
