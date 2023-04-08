package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.mapped.ExternalAssetBase;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * A Rest for the {@link ExternalAssetBase} entity
 */
public class ExternalAssetBaseRest extends AssetBaseRest implements Serializable {
    private  String manufacturer;
    private  String deviceStatus;
    public String getManufacturer() {
        return manufacturer;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ExternalAssetBaseRest that = (ExternalAssetBaseRest) o;

        if (getManufacturer() != null ? !getManufacturer().equals(that.getManufacturer()) : that.getManufacturer() != null)
            return false;
        return getDeviceStatus() != null ? getDeviceStatus().equals(that.getDeviceStatus()) : that.getDeviceStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getManufacturer() != null ? getManufacturer().hashCode() : 0);
        result = 31 * result + (getDeviceStatus() != null ? getDeviceStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ExternalAssetBaseRest{" +
                "manufacturer='" + manufacturer + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                "} " + super.toString();
    }
}