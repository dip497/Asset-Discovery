package com.serviceops.assetdiscovery.entity.base;

import jakarta.persistence.MappedSuperclass;

import java.util.Objects;

@MappedSuperclass
public class AssetBase extends SingleBase {
    private Long refId;
    private String serialNumber;
    private String manufacturer;

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


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        AssetBase assetBase = (AssetBase) o;
        return Objects.equals(refId, assetBase.refId) && Objects.equals(serialNumber, assetBase.serialNumber)
                && Objects.equals(manufacturer, assetBase.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), refId, serialNumber, manufacturer);
    }

    @Override
    public String toString() {
        return "AssetBase{" + "refId=" + refId + ", serialNumber='" + serialNumber + '\'' + ", manufacturer='"
                + manufacturer + '\'' + '}';
    }
}
