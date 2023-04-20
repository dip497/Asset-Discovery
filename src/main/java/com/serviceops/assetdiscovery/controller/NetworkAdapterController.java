package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.NetworkAdapterRest;
import com.serviceops.assetdiscovery.service.interfaces.NetworkAdapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NetworkAdapterController {
    private final NetworkAdapterService networkAdapterService;
    private final Logger logger = LoggerFactory.getLogger(NetworkAdapterController.class);

    public NetworkAdapterController(NetworkAdapterService networkAdapterService) {
        this.networkAdapterService = networkAdapterService;
    }

    @GetMapping(value = "/{refId}/networkAdapter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NetworkAdapterRest> getNetworkAdapterList(@PathVariable("refId") long refId) {
        List<NetworkAdapterRest> networkAdapterRestList = networkAdapterService.findByRefId(refId);
        logger.debug("Finding NetworkAdapter with id --> {}", refId);
        return networkAdapterRestList;
    }

    @DeleteMapping(value = "/{refId}/networkAdapter/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void deleteNetworkAdapter(@PathVariable("refId") long refId, @PathVariable("id") Long id) {
        logger.debug("Deleting NetworkAdapter with Asset id -> {}", refId);
        networkAdapterService.delete(refId, id);
    }

    @PutMapping(value = "/{refId}/networkAdapter/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateNetworkAdapter(@PathVariable("refId") long refId, @RequestBody NetworkAdapterRest networkAdapterRest, @PathVariable long id) {
        logger.debug("Updating NetworkAdapter with Asset id -> {}", refId);
        networkAdapterService.update(networkAdapterRest, refId, id);
    }
}
