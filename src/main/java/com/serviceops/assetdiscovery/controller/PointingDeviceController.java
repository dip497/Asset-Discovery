package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.service.interfaces.PointingDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class PointingDeviceController {
    private final PointingDeviceService pointingDeviceService;
    private final Logger logger = LoggerFactory.getLogger(PointingDeviceController.class);

    public PointingDeviceController(PointingDeviceService pointingDeviceService) {
        this.pointingDeviceService = pointingDeviceService;
    }

    public void addPointingDevice(){

    }
}
