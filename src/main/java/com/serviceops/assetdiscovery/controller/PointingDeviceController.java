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
    private static final Logger logger = LoggerFactory.getLogger(PointingDeviceController.class);
    private final PointingDeviceService pointingDeviceService;

    public PointingDeviceController(PointingDeviceService pointingDeviceService) {
        this.pointingDeviceService = pointingDeviceService;
    }

    @GetMapping(value = "/{refId}/pointingDevices", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PointingDeviceRest> getPointingDeviceList(@PathVariable("refId") long refId) {
        logger.debug("getPointing DeviceList with Asset id: -->{}", refId);
        return pointingDeviceService.findAllByRefId(refId);
    }

    @PutMapping(value = "/{refId}/pointingDevices/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PointingDeviceRest updatePointingDevice(@PathVariable("refId") long refId, @PathVariable("id") long id, @RequestBody PointingDeviceRest pointingDeviceRest) {
        logger.debug("PointingDevice updating with refId --> {} and id --> {}", refId, id);
        return pointingDeviceService.updateById(refId, id, pointingDeviceRest);
    }

    @DeleteMapping(value = "/{refId}/pointingDevices/{id}")
    public boolean deletePointingDevice(@PathVariable("refId") long refId, @PathVariable("id") long id) {
        logger.debug("PointingDevice deleting with refId --> {} and id --> {}", refId, id);
        return pointingDeviceService.deleteById(refId, id);
    }
}
