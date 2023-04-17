package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.LogicalDiskRest;
import com.serviceops.assetdiscovery.service.interfaces.LogicalDiskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LogicalDiskController {

    private final LogicalDiskService logicalDiskService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public LogicalDiskController(LogicalDiskService logicalDiskService){

        this.logicalDiskService = logicalDiskService;

    }
    @GetMapping(value = "/{refId}/logicaldisk",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogicalDiskRest> getLogicalDisks(@PathVariable("refId") Long refId){

        logger.info("Fetching LogicalDisks with Asset id -> {}",refId);

        List<LogicalDiskRest> logicalDiskRests = logicalDiskService.getAllLogicalDisks(refId);

        return logicalDiskRests;

    }
    @DeleteMapping("/{refId}/logicaldisk/{id}")
    public void deleteById(@PathVariable("refId") Long refId,@PathVariable("id") Long id){

        logger.info("deleting LogicalDisks with Asset id -> {}",refId);

        logicalDiskService.deleteById(refId,id);

    }
    @PutMapping(value = "/{refId}/logicaldisk/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateLogicalDiskById(@PathVariable("refId") Long refId,@PathVariable("id") Long id,@RequestBody LogicalDiskRest logicalDiskRest){

        logger.debug("updating LogicalDisks with Asset id -> {}",refId);

        logicalDiskService.update(refId,id,logicalDiskRest);
    }

}
