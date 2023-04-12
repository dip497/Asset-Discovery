package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.HardwarePropertiesRest;
import com.serviceops.assetdiscovery.service.interfaces.HardwarePropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{refId}/hardwareProperties")
public class HardwarePropertiesController {

    private final HardwarePropertiesService hardwarePropertiesService;
    Logger logger = LoggerFactory.getLogger(HardwarePropertiesController.class);

    public HardwarePropertiesController(HardwarePropertiesService hardwarePropertiesService) {
        this.hardwarePropertiesService = hardwarePropertiesService;
    }

    @GetMapping()
    public HardwarePropertiesRest findByRefId(@PathVariable("refId") Long refId){
        logger.info("Fetching Hardware Properties with Asset Id ->{}",refId);
        return hardwarePropertiesService.findByRefId(refId);
    }
}
