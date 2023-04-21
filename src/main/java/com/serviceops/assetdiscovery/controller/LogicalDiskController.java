package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.LogicalDiskRest;
import com.serviceops.assetdiscovery.service.interfaces.LogicalDiskService;
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
public class LogicalDiskController {

    private static final Logger logger = LoggerFactory.getLogger(LogicalDiskController.class);
    private final LogicalDiskService logicalDiskService;

    public LogicalDiskController(LogicalDiskService logicalDiskService) {

        this.logicalDiskService = logicalDiskService;

    }

    @GetMapping(value = "/{refId}/logicaldisk", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogicalDiskRest> getLogicalDisks(@PathVariable("refId") long refId) {

        logger.info("Fetching LogicalDisks with Asset id -> {}", refId);

        List<LogicalDiskRest> logicalDiskRests = logicalDiskService.findAllByRefId(refId);

        return logicalDiskRests;

    }

    @PutMapping(value = "/{refId}/logicaldisk/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateLogicalDisk(@PathVariable("refId") long refId, @PathVariable("id") long id,
            @RequestBody LogicalDiskRest logicalDiskRest) {

        logger.debug("updating LogicalDisks with Asset id -> {}", refId);

        logicalDiskService.updateById(refId, id, logicalDiskRest);
    }

    @DeleteMapping("/{refId}/logicaldisk/{id}")
    public boolean deleteLogicalDisk(@PathVariable("refId") long refId, @PathVariable("id") long id) {

        logger.info("deleting LogicalDisks with Asset id -> {}", refId);

        return logicalDiskService.deleteById(refId, id);

    }



}
