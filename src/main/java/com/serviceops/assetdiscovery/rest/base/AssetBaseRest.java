package com.serviceops.assetdiscovery.rest.base;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.serviceops.assetdiscovery.entity.base.AssetBase} entity
 */
public class AssetBaseRest extends SingleBaseRest implements Serializable {
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
        AssetBaseRest that = (AssetBaseRest) o;
        return Objects.equals(refId, that.refId) && Objects.equals(serialNumber, that.serialNumber)
                && Objects.equals(manufacturer, that.manufacturer);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getRefId() != null ? getRefId().hashCode() : 0);
        result = 31 * result + (getSerialNumber() != null ? getSerialNumber().hashCode() : 0);
        result = 31 * result + (getManufacturer() != null ? getManufacturer().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AssetBaseRest{" + "refId=" + refId + ", serialNumber='" + serialNumber + '\''
                + ", manufacturer='" + manufacturer + '\'' + "} " + super.toString();
    }
}