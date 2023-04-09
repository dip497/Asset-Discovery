package com.serviceops.assetdiscovery.entity.base;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AssetBase extends SingleBase {
    private Long refId;
    private String serialNumber;
    private String manufacturer;
    private String deviceStatus;

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

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

        AssetBase assetBase = (AssetBase) o;

        if (getRefId() != null ? !getRefId().equals(assetBase.getRefId()) : assetBase.getRefId() != null) return false;
        if (getSerialNumber() != null ? !getSerialNumber().equals(assetBase.getSerialNumber()) : assetBase.getSerialNumber() != null)
            return false;
        if (getManufacturer() != null ? !getManufacturer().equals(assetBase.getManufacturer()) : assetBase.getManufacturer() != null)
            return false;
        return getDeviceStatus() != null ? getDeviceStatus().equals(assetBase.getDeviceStatus()) : assetBase.getDeviceStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getRefId() != null ? getRefId().hashCode() : 0);
        result = 31 * result + (getSerialNumber() != null ? getSerialNumber().hashCode() : 0);
        result = 31 * result + (getManufacturer() != null ? getManufacturer().hashCode() : 0);
        result = 31 * result + (getDeviceStatus() != null ? getDeviceStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AssetBase{" +
                "refId=" + refId +
                ", serialNumber='" + serialNumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                "} " + super.toString();
    }
}
