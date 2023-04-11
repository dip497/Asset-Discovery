package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.MotherBoardRest;
import com.serviceops.assetdiscovery.rest.RamRest;
import com.serviceops.assetdiscovery.service.interfaces.MotherBoardService;
import com.serviceops.assetdiscovery.service.interfaces.RamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{refId}/ram")
public class RamController {
    private final RamService ramService;
    private final Logger logger = LoggerFactory.getLogger(RamController.class);

    public RamController(RamService motherBoardService) {
        this.ramService = motherBoardService;
    }

    @GetMapping()
    public List<RamRest> getRam(@PathVariable("refId") long refId){

        logger.debug("Fetching Ram with Asset id -> {}",refId);

        return ramService.findAllByRefId(refId);
    }

    @DeleteMapping("/{id}")
    public void deleteRam(@PathVariable("id") long id){

        logger.debug("Deleting Ram with  id -> {}",id);

        ramService.deleteById(id);

    }

    @PutMapping("/{id}")
    public void updateRam(@PathVariable("id") long id,@RequestBody RamRest ramRest){

        logger.debug("Updating Ram with  id -> {}",id);

        ramService.update(ramRest);

    }
}
