package com.serviceops.assetdiscovery.utils.mapper;
import com.serviceops.assetdiscovery.entity.PointingDevice;
import com.serviceops.assetdiscovery.rest.PointingDeviceRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;


public class PointingDeviceOps extends AssetBaseOps<PointingDevice, PointingDeviceRest> {
    private PointingDevice pointingDevice;
    private PointingDeviceRest pointingDeviceRest;

    public PointingDeviceOps(PointingDevice pointingDevice, PointingDeviceRest pointingDeviceRest) {
        super(pointingDevice, pointingDeviceRest);
        this.pointingDevice = pointingDevice;
        this.pointingDeviceRest = pointingDeviceRest;
    }

    public PointingDevice restToEntity() {
        super.restToEntity(pointingDeviceRest);
        pointingDevice.setNumberOfButtons(pointingDeviceRest.getNumberOfButtons());
        pointingDevice.setDescription(pointingDeviceRest.getDescription());
        pointingDevice.setPointingType(pointingDeviceRest.getPointingType());
        return pointingDevice;
    }

    public PointingDeviceRest entityToRest( ) {
        super.entityToRest(pointingDevice);
        pointingDeviceRest.setNumberOfButtons(pointingDevice.getNumberOfButtons());
        pointingDeviceRest.setDescription(pointingDevice.getDescription());
        pointingDeviceRest.setPointingType(pointingDevice.getPointingType());
        return  pointingDeviceRest;
    }


}
