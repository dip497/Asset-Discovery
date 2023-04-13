package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.BiosRest;
import com.serviceops.assetdiscovery.service.impl.BiosServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/{refId}/bios")
public class BiosController {

    private final BiosServiceImpl biosService;
    Logger logger = LoggerFactory.getLogger(BiosController.class);

    public BiosController(BiosServiceImpl biosService) {
        this.biosService = biosService;
    }

    @GetMapping()
    public List<BiosRest> getBios(@PathVariable("refId") Long refId){

        logger.debug("Fetching Bios with Asset id -> {}",refId);

        return  biosService.findByRefId(refId);
    }

    @DeleteMapping()
    public void deleteBios(@PathVariable("refId") Long refId){

        logger.debug("Deleting Bios with Asset id -> {}",refId);

        biosService.deleteByRefId(refId);

    }

    @PutMapping()
    public void updateBios(@PathVariable("refId") long refId,@RequestBody BiosRest biosRest){

        logger.debug("Updating Bios with Asset id -> {}",refId);

        biosService.update(biosRest);

    }


}
