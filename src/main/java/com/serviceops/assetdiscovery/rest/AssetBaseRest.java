package com.serviceops.assetdiscovery.rest;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * A DTO for the {@link com.serviceops.assetdiscovery.entity.mapped.AssetBase} entity
 */
public class AssetBaseRest extends SingleBaseRest implements Serializable {
    private  Long refId;
    private  String serialNumber;

    public Long getRefId() {
        return refId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AssetBaseRest that = (AssetBaseRest) o;

        if (getRefId() != null ? !getRefId().equals(that.getRefId()) : that.getRefId() != null) return false;
        return getSerialNumber() != null ? getSerialNumber().equals(that.getSerialNumber()) : that.getSerialNumber() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getRefId() != null ? getRefId().hashCode() : 0);
        result = 31 * result + (getSerialNumber() != null ? getSerialNumber().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AssetBaseRest{" +
                "refId=" + refId +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}