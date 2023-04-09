package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.service.interfaces.AssetService;
import com.serviceops.assetdiscovery.service.interfaces.MotherBoardService;
import com.serviceops.assetdiscovery.service.interfaces.NetworkScanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NetworkScanController {
    private final NetworkScanService networkScanService;
    private final Logger logger = LoggerFactory.getLogger(NetworkScanController.class);

    public NetworkScanController(NetworkScanService networkScanService) {
        this.networkScanService = networkScanService;
    }
    @GetMapping("/scan")
    public void scan(){
        logger.debug("Scanning");
        networkScanService.scan();
    }
}
