package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.NetworkScanRest;
import com.serviceops.assetdiscovery.rest.SchedulerRest;
import com.serviceops.assetdiscovery.service.interfaces.NetworkScanService;
import com.serviceops.assetdiscovery.service.interfaces.SchedulersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NetworkScanController {
    private final NetworkScanService networkScanService;
    private final SchedulersService schedulersService;
    private final Logger logger = LoggerFactory.getLogger(NetworkScanController.class);

    public NetworkScanController(NetworkScanService networkScanService, SchedulersService schedulersService) {
        this.networkScanService = networkScanService;
        this.schedulersService = schedulersService;
    }

    @GetMapping("/networkScan/scan/{id}")
    public void scan(@PathVariable long id) {
        logger.debug("Scanning");
        networkScanService.scan(id);
    }

    @PostMapping(value = "/networkScan", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveNetworkScan(@RequestBody NetworkScanRest networkScanRest) {
        logger.debug("saving network scan -> {}", networkScanRest.getName());
        networkScanService.save(networkScanRest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/networkScan/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateNetworkScan(@PathVariable long id, @RequestBody NetworkScanRest networkScanRest) {
        logger.debug("updating network scan -> {}", networkScanRest.getId());
        networkScanService.updateById(id, networkScanRest);

    }

    @GetMapping(value = "/networkScan", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NetworkScanRest> getAllNetworkScan() {
        logger.debug("fetching all network scan");
        return networkScanService.findAll();
    }

    @GetMapping(value = "/networkScan/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NetworkScanRest getNetworkScanById(@PathVariable long id) {
        logger.debug("fetching network scan by id ->{}", id);
        return networkScanService.findById(id);
    }

    @DeleteMapping("/networkScan/{id}")
    public void deleteNetworkScanById(@PathVariable long id) {
        logger.debug("deleting network scan by id ->{}", id);
        networkScanService.deleteById(id);
        schedulersService.deleteByNetworkScanId(id);
    }

    @GetMapping(value = "/schedulers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SchedulerRest> getSchedulers() {
        return schedulersService.findAll();
    }
}
