package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.OSRest;
import com.serviceops.assetdiscovery.service.impl.OsServiceImpl;
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
public class OSController {
    private static final Logger logger = LoggerFactory.getLogger(OSController.class);
    private final OsServiceImpl osService;

    public OSController(OsServiceImpl osService) {
        this.osService = osService;
    }

    @GetMapping(value = "/{refId}/os", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OSRest> getByRefId(@PathVariable("refId") long refId) {
        logger.debug("Fetching OS with Asset id -> {}", refId);
        return osService.findByRefId(refId);
    }

    @DeleteMapping("/{refId}/os")
    public boolean delete(@PathVariable("refId") long refId) {
        logger.debug("Deleting Bios with Asset id -> {}", refId);
        return osService.deleteByRefId(refId);
    }

    @PutMapping(value = "/{refId}/os", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OSRest update(@PathVariable("refId") long refId, @RequestBody OSRest osRest) {
        logger.debug("Updating Bios with Asset id -> {}", refId);
        return osService.updateByRefId(refId, osRest);
    }
}
