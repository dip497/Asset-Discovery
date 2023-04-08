package com.serviceops.assetdiscovery.rest;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.PointingDevice} entity
 */
public class PointingDeviceRest extends AssetBaseRest implements Serializable {
    private   int numberOfButtons;
    private   String description;
    private   String pointingType;
    private   String pnpDeviceId;

    public int getNumberOfButtons() {
        return numberOfButtons;
    }

    public String getDescription() {
        return description;
    }

    public String getPointingType() {
        return pointingType;
    }

    public String getPnpDeviceId() {
        return pnpDeviceId;
    }

    public void setNumberOfButtons(int numberOfButtons) {
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
        PointingDeviceRest entity = (PointingDeviceRest) o;
        return Objects.equals(this.numberOfButtons, entity.numberOfButtons) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.pointingType, entity.pointingType) &&
                Objects.equals(this.pnpDeviceId, entity.pnpDeviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfButtons, description, pointingType, pnpDeviceId);
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