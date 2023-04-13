package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.PointingDeviceRest;
import com.serviceops.assetdiscovery.service.interfaces.PointingDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{refId}/pointingDevices")
public class PointingDeviceController {
    private final PointingDeviceService pointingDeviceService;
    private final Logger logger = LoggerFactory.getLogger(PointingDeviceController.class);

    public PointingDeviceController(PointingDeviceService pointingDeviceService) {
        this.pointingDeviceService = pointingDeviceService;
    }

    @GetMapping()
    public List<PointingDeviceRest> getPointingDeviceList(@PathVariable("refId") Long refId){
        logger.debug("getPointing DeviceList with Asset id: -->{}",refId);
        List<PointingDeviceRest> pointingDeviceList = pointingDeviceService.getPointingDevices(refId);

        return pointingDeviceList;
    }

    @DeleteMapping()
    public void deletePointingDevice(@PathVariable("refId") Long refId){
        logger.debug("PointingDevice deleting with id --> {}",refId);
        pointingDeviceService.deleteById(refId);
    }

    @PutMapping()
    public void updatePointingDevice(@PathVariable("refId") String refId, @RequestBody PointingDeviceRest pointingDeviceRest){
        logger.debug("PointingDevice updating with id --> {}",refId);
        pointingDeviceService.update(Long.parseLong(refId), pointingDeviceRest);
    }
}
