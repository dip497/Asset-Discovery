package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.entity.NetworkAdapter;
import com.serviceops.assetdiscovery.rest.NetworkAdapterRest;
import com.serviceops.assetdiscovery.service.interfaces.NetworkAdapterService;
import jdk.jfr.Registered;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Registered
@RequestMapping("/{refId}/networkAdapter")
public class NetworkAdapterController {
    private final NetworkAdapterService networkAdapterService;
    private final Logger logger = LoggerFactory.getLogger(NetworkAdapterController.class);

    public NetworkAdapterController(NetworkAdapterService networkAdapterService) {
        this.networkAdapterService = networkAdapterService;
    }

    @GetMapping
    public List<NetworkAdapterRest> getNetworkAdapterList(@PathVariable("refId") long id) {
        List<NetworkAdapterRest> networkAdapterRestList = new ArrayList<>();
        networkAdapterRestList.add(networkAdapterService.findByResId(id));
        logger.debug("Finding NetworkAdapter with id --> {}",id);
        return networkAdapterRestList;
    }

    @DeleteMapping
    public void deleteNetworkAdapter(@PathVariable("refId") long id){
        logger.debug("Deleting NetworkAdapter with Asset id -> {}",id);
        networkAdapterService.delete(id);
    }

    @PutMapping
    public void updateNetworkAdapter(@PathVariable("refId") long id,@RequestBody NetworkAdapterRest networkAdapterRest){
        logger.debug("Updating NetworkAdapter with Asset id -> {}",id);
        networkAdapterService.update(networkAdapterRest);
    }
}
