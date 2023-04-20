package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.ComputerSystemRest;
import com.serviceops.assetdiscovery.service.interfaces.ComputerSystemService;
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
public class ComputerSystemController {

    private final ComputerSystemService computerSystemService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ComputerSystemController(ComputerSystemService computerSystemService) {
        this.computerSystemService = computerSystemService;
    }

    @GetMapping(value = "/{refId}/computersystem", name = "ComputerSystemDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ComputerSystemRest> getComputerSystem(@PathVariable("refId") long refId) {


        List<ComputerSystemRest> computerSystemRests = computerSystemService.get(refId);

        logger.info("Fetching ComputerSystem with Asset id -> {}", refId);

        return computerSystemRests;
    }

    @DeleteMapping(value = "/{refId}/computersystem")
    public void deleteComputerSystem(@PathVariable("refId") long refId) {

        logger.info("Deleting ComputerSystem with Asset id -> {}", refId);

        computerSystemService.deleteById(refId);
    }

    @PutMapping(value = "/{refId}/computersystem", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateComputerSystem(@PathVariable("refId") long refId,
            @RequestBody ComputerSystemRest computerSystemRest) {

        logger.info("Updating updateComputerSystem with Asset id -> {}", refId);

        computerSystemService.update(refId, computerSystemRest);
    }

}
