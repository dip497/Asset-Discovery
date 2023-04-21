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
    private static final Logger logger = LoggerFactory.getLogger(BiosController.class);
    private final BiosServiceImpl biosService;

    public BiosController(BiosServiceImpl biosService) {
        this.biosService = biosService;
    }

    @GetMapping(value = "/{refId}/bios", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BiosRest> getByRefId(@PathVariable("refId") long refId) {
        logger.debug("Fetching Bios with Asset id -> {}", refId);
        return biosService.findByRefId(refId);
    }

    @PutMapping(value = "/{refId}/bios", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BiosRest update(@PathVariable("refId") long refId, @RequestBody BiosRest biosRest) {
        logger.debug("Updating Bios with Asset id -> {}", refId);
        return biosService.update(refId, biosRest);
    }

    @DeleteMapping("/{refId}/bios")
    public boolean delete(@PathVariable("refId") long refId) {
        logger.debug("Deleting Bios with Asset id -> {}", refId);
        return biosService.deleteByRefId(refId);
    }
}
