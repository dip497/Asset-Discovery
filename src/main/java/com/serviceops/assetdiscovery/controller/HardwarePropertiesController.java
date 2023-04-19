package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.HardwarePropertiesRest;
import com.serviceops.assetdiscovery.service.interfaces.HardwarePropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HardwarePropertiesController {

    private final HardwarePropertiesService hardwarePropertiesService;
    Logger logger = LoggerFactory.getLogger(HardwarePropertiesController.class);

    public HardwarePropertiesController(HardwarePropertiesService hardwarePropertiesService) {
        this.hardwarePropertiesService = hardwarePropertiesService;
    }

    @GetMapping(value = "/{refId}/hardwareProperties" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HardwarePropertiesRest> findByRefId(@PathVariable("refId") Long refId){

        List<HardwarePropertiesRest> hardwarePropertiesRests = new ArrayList<>();

        logger.debug("Fetching Hardware Properties with Asset Id ->{}",refId);

        hardwarePropertiesRests.add(hardwarePropertiesService.findByRefId(refId));

        return hardwarePropertiesRests;
    }

}
