package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.OSRest;
import com.serviceops.assetdiscovery.service.impl.OsServiceImpl;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{refId}/os")
public class OSController {

    private final OsServiceImpl osService;
    Logger logger = LoggerFactory.getLogger(OSController.class);

    public OSController(OsServiceImpl osService) {
        this.osService = osService;
    }

    @GetMapping()
    public List<OSRest> getBios(@PathVariable("refId") Long refId){

        List<OSRest> osRests = new ArrayList<>();

        logger.debug("Fetching OS with Asset id -> {}",refId);

        osRests.add(osService.findByRefId(refId));

        return osRests;
    }

    @DeleteMapping()
    public void deleteBios(@PathVariable("refId") Long refId){

        logger.debug("Deleting Bios with Asset id -> {}",refId);

        osService.deleteByRefId(refId);

    }

    @PutMapping()
    public void updateBios(@PathVariable("refId") long refId,@RequestBody OSRest osRest){

        logger.debug("Updating Bios with Asset id -> {}",refId);

        osService.update(osRest);

    }

}
