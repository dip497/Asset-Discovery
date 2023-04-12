package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.ComputerSystemRest;
import com.serviceops.assetdiscovery.service.interfaces.ComputerSystemService;
import com.serviceops.assetdiscovery.utils.mapper.ComputerSystemOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/{refId}/computersystem")
public class ComputerSystemController {

    private final ComputerSystemService computerSystemService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ComputerSystemController(ComputerSystemService computerSystemService){
        this.computerSystemService = computerSystemService;
    }

    @GetMapping()
    public ComputerSystemRest getComputerSystem(@PathVariable("refID") Long id){
        logger.debug("Request hits the get api of computer system");
        ComputerSystemRest computerSystemRest = computerSystemService.get(id);
        return computerSystemRest;
    }
    @DeleteMapping()
    public void deleteComputerSystem(@PathVariable("refId") Long id){
        logger.debug("Request hits the delete api of computer system");
        computerSystemService.deleteById(id);
    }
    @PutMapping()
    public void updateComputerSystem(@RequestBody ComputerSystemRest computerSystemRest){
        logger.debug("Request hits the update api of computer system");
        computerSystemService.update(computerSystemRest);
    }

}
