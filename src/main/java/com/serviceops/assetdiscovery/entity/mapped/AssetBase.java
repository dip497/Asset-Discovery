package com.serviceops.assetdiscovery.entity.mapped;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AssetBase extends SingleBase {
    private Long refId;
    private String serialNumber;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AssetBase assetBase = (AssetBase) o;

        if (getRefId() != null ? !getRefId().equals(assetBase.getRefId()) : assetBase.getRefId() != null) return false;
        return getSerialNumber() != null ? getSerialNumber().equals(assetBase.getSerialNumber()) : assetBase.getSerialNumber() == null;
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
        return "AssetBase{" +
                "refId=" + refId +
                ", serialNumber='" + serialNumber + '\'' +
                "} " + super.toString();
    }
}
