package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.ComputerPropertiesRest;
import com.serviceops.assetdiscovery.service.interfaces.ComputerPropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{refId}/computerProperties")
public class ComputerPropertiesController {

    private final ComputerPropertiesService computerPropertiesService;
    Logger logger = LoggerFactory.getLogger(ComputerPropertiesController.class);

    public ComputerPropertiesController(ComputerPropertiesService computerPropertiesService) {
        this.computerPropertiesService = computerPropertiesService;
    }


    @GetMapping()
    public List<ComputerPropertiesRest> findByRefId(@PathVariable("refId")Long refId){

        List<ComputerPropertiesRest> computerPropertiesRests = new ArrayList<>();

        logger.debug("Fetching Hardware Properties with Asset Id ->{}",refId);

        computerPropertiesRests.add(computerPropertiesService.findByRefId(refId));

        return computerPropertiesRests;

    }

    @PutMapping
    public void updateComputerProperties(@PathVariable("refId") Long refId,@RequestBody ComputerPropertiesRest computerPropertiesRest){

        logger.debug("Updating Hardware Properties with Asset Id ->{}",refId);

        computerPropertiesService.update(refId,computerPropertiesRest);

    }


}
