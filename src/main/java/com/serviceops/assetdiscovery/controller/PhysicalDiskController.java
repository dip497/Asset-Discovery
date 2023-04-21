package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;
import com.serviceops.assetdiscovery.service.interfaces.PhysicalDiskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhysicalDiskController {
    private final PhysicalDiskService physicalDiskService;
    private static final Logger logger = LoggerFactory.getLogger(PhysicalDiskController.class);

    public PhysicalDiskController(PhysicalDiskService physicalDiskService) {
        this.physicalDiskService = physicalDiskService;
    }

    @GetMapping(value = "/{refId}/physicalDisk", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PhysicalDiskRest> getPhysicalDiskList(@PathVariable("refId") long refId) {
        logger.debug("Fetching physical disk with id: --> {}", refId);
        return physicalDiskService.findByRefId(refId);
    }

    @PutMapping(value = "/{refId}/physicalDisk", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PhysicalDiskRest updatePhysicalDisk(@PathVariable("refId") long refId, @RequestBody PhysicalDiskRest physicalDiskRest) {
        logger.debug("Updating physical disk with id: --> {}", refId);
        return physicalDiskService.updateByRefId(refId, physicalDiskRest);
    }

    @DeleteMapping("/{refId}/physicalDisk")
    public boolean deletePhysicalDisk(@PathVariable("refId") long refId) {
        logger.debug("Deleting physical disk with id: --> {}", refId);
        return physicalDiskService.deleteByRefId(refId);
    }
}
