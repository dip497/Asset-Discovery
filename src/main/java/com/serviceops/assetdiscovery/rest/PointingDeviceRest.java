package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.PointingDevice} entity
 */
public class PointingDeviceRest extends AssetBaseRest implements Serializable {
    private int numberOfButtons;
    private String description;
    private String pointingType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PointingDeviceRest that = (PointingDeviceRest) o;
        return numberOfButtons == that.numberOfButtons && Objects.equals(description, that.description) && Objects.equals(pointingType, that.pointingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfButtons, description, pointingType);
    }

    @Override
    public String toString() {
        return "PointingDeviceRest{" +
                "numberOfButtons=" + numberOfButtons +
                ", description='" + description + '\'' +
                ", pointingType='" + pointingType + '\'' +
                '}';
    }
}