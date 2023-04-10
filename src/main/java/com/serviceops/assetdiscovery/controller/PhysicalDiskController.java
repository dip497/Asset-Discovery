package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.service.interfaces.PhysicalDiskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhysicalDiskController {
    private final PhysicalDiskService physicalDiskService;
    private final Logger logger = LoggerFactory.getLogger(PhysicalDiskController.class);
    public PhysicalDiskController(PhysicalDiskService physicalDiskService) {
        this.physicalDiskService = physicalDiskService;
    }

    @GetMapping("physicalDisk")
    public void save(Long id) {
        logger.info("Saving physical disk with id: " + id);
        physicalDiskService.save(id);
    }
}
