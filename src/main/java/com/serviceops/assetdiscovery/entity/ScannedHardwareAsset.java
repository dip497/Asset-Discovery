package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AuditBase;
import jakarta.persistence.Entity;

@Entity
public class ScannedHardwareAsset extends AuditBase {
    private long assetId;
    private String macAddress;

    public long getAssetId() {
        return assetId;
    }

    public void setAssetId(long assetId) {
        this.assetId = assetId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String mascAddress) {
        this.macAddress = mascAddress;
    }
}
