package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.BiosRest;
import com.serviceops.assetdiscovery.service.impl.BiosServiceImpl;
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
public class BiosController {

    private final BiosServiceImpl biosService;
    Logger logger = LoggerFactory.getLogger(BiosController.class);

    public BiosController(BiosServiceImpl biosService) {
        this.biosService = biosService;
    }

    @GetMapping(value = "/{refId}/bios", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BiosRest> getByRefId(@PathVariable("refId") Long refId) {

        logger.debug("Fetching Bios with Asset id -> {}", refId);

        return biosService.findByRefId(refId);
    }

    @DeleteMapping("/{refId}/bios")
    public void delete(@PathVariable("refId") Long refId) {

        logger.debug("Deleting Bios with Asset id -> {}", refId);

        biosService.deleteByRefId(refId);

    }

    @PutMapping(value = "/{refId}/bios", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("refId") long refId, @RequestBody BiosRest biosRest) {

        logger.debug("Updating Bios with Asset id -> {}", refId);

        biosService.update(refId, biosRest);

    }


}
