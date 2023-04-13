package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.ComputerSystemRest;
import com.serviceops.assetdiscovery.service.interfaces.ComputerSystemService;
import com.serviceops.assetdiscovery.utils.mapper.ComputerSystemOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{refId}/computerSystem")
public class ComputerSystemController {

    private final ComputerSystemService computerSystemService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ComputerSystemController(ComputerSystemService computerSystemService){
        this.computerSystemService = computerSystemService;
    }

    @GetMapping()
    public List<ComputerSystemRest> getComputerSystem(@PathVariable("refId") Long refId){

        List<ComputerSystemRest> computerSystemRests = new ArrayList<>();

        ComputerSystemRest computerSystemRest = computerSystemService.get(refId);

        computerSystemRests.add(computerSystemRest);

        logger.debug("Fetching ComputerSystem with Asset id -> {}",refId);

        return computerSystemRests;
    }
    @DeleteMapping()
    public void deleteComputerSystem(@PathVariable("refId") Long refId){

        logger.debug("Deleting ComputerSystem with Asset id -> {}",refId);

        computerSystemService.deleteById(refId);
    }
    @PutMapping()
    public void updateComputerSystem(@PathVariable("refId") Long refId,@RequestBody ComputerSystemRest computerSystemRest){

        logger.debug("Updating updateComputerSystem with Asset id -> {}",refId);

        computerSystemService.update(computerSystemRest);
    }

}
