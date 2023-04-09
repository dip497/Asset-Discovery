package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

@Entity
public class PointingDevice extends AssetBase {
    private int numberOfButtons;
    private String description;
    private String pointingType;
    private String pnpDeviceId;

    public int getNumberOfButtons() {
        return numberOfButtons;
    }

    public void setNumberOfButtons(int numberOfButtons) {
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

        PointingDevice that = (PointingDevice) o;

        if (getNumberOfButtons() != that.getNumberOfButtons()) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getPointingType() != null ? !getPointingType().equals(that.getPointingType()) : that.getPointingType() != null)
            return false;
        return getPnpDeviceId() != null ? getPnpDeviceId().equals(that.getPnpDeviceId()) : that.getPnpDeviceId() == null;
    }

    @Override
    public int hashCode() {
        int result = getNumberOfButtons();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getPointingType() != null ? getPointingType().hashCode() : 0);
        result = 31 * result + (getPnpDeviceId() != null ? getPnpDeviceId().hashCode() : 0);
        return result;
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
