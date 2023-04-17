package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class PointingDevice extends AssetBase {
    private String numberOfButtons;
    private String description;
    private String pointingType;
    private String pnpDeviceId;

    public String getNumberOfButtons() {
        return numberOfButtons;
    }

    public void setNumberOfButtons(String numberOfButtons) {
        this.numberOfButtons = numberOfButtons;
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

    public String getPnpDeviceId() {
        return pnpDeviceId;
    }

    public void setPnpDeviceId(String pnpDeviceId) {
        this.pnpDeviceId = pnpDeviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PointingDevice that = (PointingDevice) o;
        return Objects.equals(numberOfButtons, that.numberOfButtons) && Objects.equals(description, that.description) && Objects.equals(pointingType, that.pointingType) && Objects.equals(pnpDeviceId, that.pnpDeviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfButtons, description, pointingType, pnpDeviceId);
    }

    @Override
    public String toString() {
        return "PointingDevice{" +
                "numberOfButtons=" + numberOfButtons +
                ", description='" + description + '\'' +
                ", pointingType='" + pointingType + '\'' +
                ", pnpDeviceId='" + pnpDeviceId + '\'' +
                '}' + super.toString();
    }
}
