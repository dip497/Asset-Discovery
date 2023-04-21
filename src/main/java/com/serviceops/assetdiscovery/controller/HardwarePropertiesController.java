package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.HardwarePropertiesRest;
import com.serviceops.assetdiscovery.service.interfaces.HardwarePropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HardwarePropertiesController {
    private static final Logger logger = LoggerFactory.getLogger(HardwarePropertiesController.class);
    private final HardwarePropertiesService hardwarePropertiesService;

    public HardwarePropertiesController(HardwarePropertiesService hardwarePropertiesService) {
        this.hardwarePropertiesService = hardwarePropertiesService;
    }

    @GetMapping(value = "/{refId}/hardwareProperties", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HardwarePropertiesRest> findByRefId(@PathVariable("refId") long refId) {
        logger.debug("Fetching Hardware Properties with Asset Id ->{}", refId);
        return hardwarePropertiesService.findByRefId(refId);
    }
}
