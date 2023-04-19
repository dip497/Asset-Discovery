package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.ComputerPropertiesRest;
import com.serviceops.assetdiscovery.service.interfaces.ComputerPropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ComputerPropertiesController {

    private final ComputerPropertiesService computerPropertiesService;
    Logger logger = LoggerFactory.getLogger(ComputerPropertiesController.class);

    public ComputerPropertiesController(ComputerPropertiesService computerPropertiesService) {
        this.computerPropertiesService = computerPropertiesService;
    }


    @GetMapping(value = "/{refId}/computerProperties",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ComputerPropertiesRest> findByRefId(@PathVariable("refId")Long refId){

        List<ComputerPropertiesRest> computerPropertiesRests = new ArrayList<>();

        logger.debug("Fetching Hardware Properties with Asset Id ->{}",refId);

        computerPropertiesRests.add(computerPropertiesService.findByRefId(refId));

        return computerPropertiesRests;

    }

}
