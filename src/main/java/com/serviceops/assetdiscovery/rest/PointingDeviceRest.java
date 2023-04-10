package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.PointingDevice} entity
 */
public class PointingDeviceRest extends AssetBaseRest implements Serializable {
    private   String numberOfButtons; // TODO make it long or int if possible
    private   String description;
    private   String pointingType;
    private   String pnpDeviceId;

    public String getDescription() {
        return description;
    }

    public String getPointingType() {
        return pointingType;
    }

    public String getPnpDeviceId() {
        return pnpDeviceId;
    }

    public String getNumberOfButtons() {
        return numberOfButtons;
    }

    public void setNumberOfButtons(String numberOfButtons) {
        this.numberOfButtons = numberOfButtons;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPointingType(String pointingType) {
        this.pointingType = pointingType;
    }

    public void setPnpDeviceId(String pnpDeviceId) {
        this.pnpDeviceId = pnpDeviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PointingDeviceRest that = (PointingDeviceRest) o;
        return Objects.equals(numberOfButtons, that.numberOfButtons) && Objects.equals(description, that.description) && Objects.equals(pointingType, that.pointingType) && Objects.equals(pnpDeviceId, that.pnpDeviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfButtons, description, pointingType, pnpDeviceId);
    }

    @Override
    public String toString() {
        return "PointingDeviceRest{" +
                "numberOfButtons=" + numberOfButtons +
                ", description='" + description + '\'' +
                ", pointingType='" + pointingType + '\'' +
                ", pnpDeviceId='" + pnpDeviceId + '\'' +
                "} " + super.toString();
    }
}