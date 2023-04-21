package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AuditBaseRest;

import java.io.Serializable;

/**
 * A Rest for the entity
 */
public class HardwarePropertiesRest extends AuditBaseRest implements Serializable {
    private String serialNumber;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}