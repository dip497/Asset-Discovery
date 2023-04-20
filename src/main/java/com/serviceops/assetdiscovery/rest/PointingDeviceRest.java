package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.PointingDevice} entity
 */
public class PointingDeviceRest extends AssetBaseRest implements Serializable {
    private long refId;
    private String manufacturer;
    private int numberOfButtons;
    private String description;
    private String pointingType;

    @Override
    public long getRefId() {
        return refId;
    }

    @Override
    public void setRefId(long refId) {
        this.refId = refId;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPointingType() {
        return pointingType;
    }

    public void setPointingType(String pointingType) {
        this.pointingType = pointingType;
    }


    public int getNumberOfButtons() {
        return numberOfButtons;
    }

    public void setNumberOfButtons(int numberOfButtons) {
        this.numberOfButtons = numberOfButtons;
    }
}