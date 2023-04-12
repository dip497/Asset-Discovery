package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.service.interfaces.NetworkAdapterService;
import jdk.jfr.Registered;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Registered
public class NetworkAdapterController {
    private final NetworkAdapterService networkAdapterService;
    private final Logger logger = LoggerFactory.getLogger(NetworkAdapterController.class);

    public NetworkAdapterController(NetworkAdapterService networkAdapterService) {
        this.networkAdapterService = networkAdapterService;
    }
}
