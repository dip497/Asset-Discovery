package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.PointingDevice;
import com.serviceops.assetdiscovery.rest.PointingDeviceRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;


public class PointingDeviceOps extends SingleBaseOps<PointingDevice, PointingDeviceRest> {

    @Override
    public PointingDevice restToEntity(PointingDevice pointingDevice, PointingDeviceRest pointingDeviceRest) {
        super.restToEntity(pointingDevice, pointingDeviceRest);
        pointingDevice.setRefId(pointingDeviceRest.getRefId());
        pointingDevice.setManufacturer(pointingDeviceRest.getManufacturer());
        pointingDevice.setNumberOfButtons(pointingDeviceRest.getNumberOfButtons());
        pointingDevice.setDescription(pointingDeviceRest.getDescription());
        pointingDevice.setPointingType(pointingDeviceRest.getPointingType());
        return pointingDevice;
    }

    @Override
    public PointingDeviceRest entityToRest(PointingDevice pointingDevice, PointingDeviceRest pointingDeviceRest) {
        super.entityToRest(pointingDevice, pointingDeviceRest);
        pointingDeviceRest.setManufacturer(pointingDevice.getManufacturer());
        pointingDeviceRest.setRefId(pointingDevice.getRefId());
        pointingDeviceRest.setNumberOfButtons(pointingDevice.getNumberOfButtons());
        pointingDeviceRest.setDescription(pointingDevice.getDescription());
        pointingDeviceRest.setPointingType(pointingDevice.getPointingType());
        return pointingDeviceRest;
    }


}
