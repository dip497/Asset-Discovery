package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.PointingDevice;
import com.serviceops.assetdiscovery.rest.PointingDeviceRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;


public class PointingDeviceOps extends SingleBaseOps<PointingDevice, PointingDeviceRest> {
    private final PointingDevice pointingDevice;
    private final PointingDeviceRest pointingDeviceRest;

    public PointingDeviceOps(PointingDevice pointingDevice, PointingDeviceRest pointingDeviceRest) {
        super(pointingDevice, pointingDeviceRest);
        this.pointingDevice = pointingDevice;
        this.pointingDeviceRest = pointingDeviceRest;
    }

    public PointingDevice restToEntity() {
        super.restToEntity(pointingDeviceRest);
        pointingDevice.setRefId(pointingDeviceRest.getRefId());
        pointingDevice.setManufacturer(pointingDeviceRest.getManufacturer());
        pointingDevice.setNumberOfButtons(pointingDeviceRest.getNumberOfButtons());
        pointingDevice.setDescription(pointingDeviceRest.getDescription());
        pointingDevice.setPointingType(pointingDeviceRest.getPointingType());
        return pointingDevice;
    }

    public PointingDeviceRest entityToRest() {
        super.entityToRest(pointingDevice);
        pointingDeviceRest.setManufacturer(pointingDevice.getManufacturer());
        pointingDeviceRest.setRefId(pointingDevice.getRefId());
        pointingDeviceRest.setNumberOfButtons(pointingDevice.getNumberOfButtons());
        pointingDeviceRest.setDescription(pointingDevice.getDescription());
        pointingDeviceRest.setPointingType(pointingDevice.getPointingType());
        return pointingDeviceRest;
    }


}
