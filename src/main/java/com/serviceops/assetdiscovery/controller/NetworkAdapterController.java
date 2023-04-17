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

    @GetMapping(value = "/{refId}/networkAdapter",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NetworkAdapterRest> getNetworkAdapterList(@PathVariable("refId") long id) {
        List<NetworkAdapterRest> networkAdapterRestList = networkAdapterService.findByRefId(id);
        logger.debug("Finding NetworkAdapter with id --> {}",id);
        return networkAdapterRestList;
    }

    @DeleteMapping(value = "/{refId}/networkAdapter", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void deleteNetworkAdapter(@PathVariable("refId") long id){
        logger.debug("Deleting NetworkAdapter with Asset id -> {}",id);
        networkAdapterService.delete(id);
    }

    @PutMapping(value = "/{refId}/networkAdapter", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateNetworkAdapter(@PathVariable("refId") long id,@RequestBody NetworkAdapterRest networkAdapterRest){
        logger.debug("Updating NetworkAdapter with Asset id -> {}",id);
        networkAdapterService.update(networkAdapterRest);
    }
}
