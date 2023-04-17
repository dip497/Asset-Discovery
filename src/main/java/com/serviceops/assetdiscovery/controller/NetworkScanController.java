package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.config.NetworkScanScheduler;
import com.serviceops.assetdiscovery.rest.NetworkScanRest;
import com.serviceops.assetdiscovery.rest.SchedulerRest;
import com.serviceops.assetdiscovery.service.interfaces.NetworkScanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NetworkScanController {
    private final NetworkScanService networkScanService;

    private final Logger logger = LoggerFactory.getLogger(NetworkScanController.class);

    public NetworkScanController(NetworkScanService networkScanService )  {
        this.networkScanService = networkScanService;

    }
    @GetMapping("/networkScan/scan/{id}")
    public void scan(@PathVariable Long id){
        logger.debug("Scanning");
        networkScanService.scan(id);
    }

    @PostMapping("/networkScan")
    public void saveNetworkScan(@RequestBody NetworkScanRest networkScanRest){
        logger.debug("saving network scan -> {}" ,networkScanRest.getName());
        networkScanService.save(networkScanRest);
    }

    @PutMapping("/networkScan/{id}")
    public void updateNetworkScan(@PathVariable Long id,@RequestBody NetworkScanRest networkScanRest){
        networkScanService.update(id,networkScanRest);

    }

    @GetMapping("/networkScan")
    public List<NetworkScanRest> getAllNetworkScan(){
        logger.debug("fetching all network scan");
        return networkScanService.findAll();
    }

    @PostMapping("/networkScan/{id}/addScheduler")
    public void addScheduler(@PathVariable Long id,@RequestBody SchedulerRest schedulerRest){
        networkScanService.addScheduler(id,schedulerRest);
    }

    @GetMapping("/networkScan/{id}")
    public NetworkScanRest getNetworkScanById(@PathVariable Long id){
        logger.debug("fetching network scan by id ->{}",id);
        return networkScanService.findById(id);
    }

    @DeleteMapping("/networkScan/{id}")
    public void deleteNetworkScanById(@PathVariable Long id){
        logger.debug("deleting network scan by id ->{}",id);
        networkScanService.deleteById(id);
    }

}
