package com.serviceops.assetdiscovery.entity.mapped;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class ExternalAssetBase extends AssetBase {
    private String manufacturer;
    private String deviceStatus;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ExternalAssetBase that = (ExternalAssetBase) o;

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
        return "ExternalAssetBase{" +
                "manufacturer='" + manufacturer + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                '}'+ super.toString();
    }
}
