package com.serviceops.assetdiscovery.utils.mapper;
import com.serviceops.assetdiscovery.entity.PointingDevice;
import com.serviceops.assetdiscovery.rest.PointingDeviceRest;

public class PointingDeviceOps extends ExternalAssetBaseOps<PointingDevice, PointingDeviceRest> {
    private PointingDevice pointingDevice;
    private PointingDeviceRest pointingDeviceRest;

    public PointingDeviceOps(PointingDevice pointingDevice, PointingDeviceRest pointingDeviceRest) {
        super(pointingDevice, pointingDeviceRest);
        this.pointingDevice = pointingDevice;
        this.pointingDeviceRest = pointingDeviceRest;
    }

    @Override
    public PointingDevice restToEntity(PointingDeviceRest pointingDeviceRest) {
        super.restToEntity(pointingDeviceRest);
        pointingDevice.setNumberOfButtons(pointingDeviceRest.getNumberOfButtons());
        pointingDevice.setDescription(pointingDeviceRest.getDescription());
        pointingDevice.setPointingType(pointingDeviceRest.getPointingType());
        pointingDevice.setPnpDeviceId(pointingDeviceRest.getPnpDeviceId());
        return pointingDevice;
    }

    @Override
    public PointingDeviceRest entityToRest(PointingDevice pointingDevice) {
        super.entityToRest(pointingDevice);
        pointingDeviceRest.setNumberOfButtons(pointingDevice.getNumberOfButtons());
        pointingDeviceRest.setDescription(pointingDevice.getDescription());
        pointingDeviceRest.setPointingType(pointingDevice.getPointingType());
        pointingDeviceRest.setPnpDeviceId(pointingDevice.getPnpDeviceId());
        return  pointingDeviceRest;
    }

    public PointingDevice getPointingDevice() {
        return pointingDevice;
    }

    public void setPointingDevice(PointingDevice pointingDevice) {
        this.pointingDevice = pointingDevice;
    }

    public PointingDeviceRest getPointingDeviceRest() {
        return pointingDeviceRest;
    }

    public void setPointingDeviceRest(PointingDeviceRest pointingDeviceRest) {
        this.pointingDeviceRest = pointingDeviceRest;
    }
}
