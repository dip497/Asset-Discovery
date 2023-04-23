package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AuditBaseRest;

import java.io.Serializable;

/**
 * A Rest for the entity
 */
public class HardwarePropertiesRest extends AuditBaseRest implements Serializable {
    private long refId;
    private String serialNumber;

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}