package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.PointingDeviceRest;
import com.serviceops.assetdiscovery.service.interfaces.PointingDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PointingDeviceController {
    private final PointingDeviceService pointingDeviceService;
    private final Logger logger = LoggerFactory.getLogger(PointingDeviceController.class);

    public PointingDeviceController(PointingDeviceService pointingDeviceService) {
        this.pointingDeviceService = pointingDeviceService;
    }

    @GetMapping(value = "/{refId}/pointingDevices", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PointingDeviceRest> getPointingDeviceList(@PathVariable("refId") Long refId) {
        logger.debug("getPointing DeviceList with Asset id: -->{}", refId);
        return pointingDeviceService.getPointingDevices(refId);
    }

    @DeleteMapping(value = "/{refId}/pointingDevices")
    public void deletePointingDevice(@PathVariable("refId") Long refId) {
        logger.debug("PointingDevice deleting with id --> {}", refId);
        pointingDeviceService.deleteById(refId);
    }

    @PutMapping(value = "/{refId}/pointingDevices", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PointingDeviceRest updatePointingDevice(@PathVariable("refId") String refId, @RequestBody PointingDeviceRest pointingDeviceRest) {
        logger.debug("PointingDevice updating with id --> {}", refId);
        return pointingDeviceService.update(Long.parseLong(refId), pointingDeviceRest);
    }
}
