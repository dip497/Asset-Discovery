package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.service.interfaces.PointingDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PointingDeviceController {
    private final PointingDeviceService pointingDeviceService;


    private final Logger logger = LoggerFactory.getLogger(PointingDeviceController.class);
    public PointingDeviceController(PointingDeviceService pointingDeviceService) {
        this.pointingDeviceService = pointingDeviceService;
    }

    @GetMapping("/pointingDevice")
    public void getPointingDeviceInfo(){
        logger.debug("getting information of pointing device");
        pointingDeviceService.save();
    }
}
