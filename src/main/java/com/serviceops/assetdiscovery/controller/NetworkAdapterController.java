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
    private final static Logger logger = LoggerFactory.getLogger(NetworkAdapterController.class);
    private final NetworkAdapterService networkAdapterService;

    public NetworkAdapterController(NetworkAdapterService networkAdapterService) {
        this.networkAdapterService = networkAdapterService;
    }

    @GetMapping(value = "/{refId}/networkAdapter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NetworkAdapterRest> getNetworkAdapterList(@PathVariable("refId") long refId) {
        List<NetworkAdapterRest> networkAdapterRestList = networkAdapterService.findAllByRefId(refId);
        logger.debug("Finding NetworkAdapter with id --> {}", refId);
        return networkAdapterRestList;
    }

    @PutMapping(value = "/{refId}/networkAdapter/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public NetworkAdapterRest updateNetworkAdapter(@PathVariable("refId") long refId, @RequestBody NetworkAdapterRest networkAdapterRest, @PathVariable long id) {
        logger.debug("Updating NetworkAdapter with Asset id -> {}", refId);
        return networkAdapterService.updateById(networkAdapterRest, refId, id);
    }

    @DeleteMapping(value = "/{refId}/networkAdapter/{id}")
    public boolean deleteNetworkAdapter(@PathVariable("refId") long refId, @PathVariable("id") long id) {
        logger.debug("Deleting NetworkAdapter with Asset id--> {} and id --> {}", refId, id);
        return networkAdapterService.deleteById(refId, id);
    }
}
