package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.SingleBaseRest;

import java.io.Serializable;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Monitor} entity
 */
public class MonitorRest extends SingleBaseRest implements Serializable {

    private long refId;
    private String description;
    private String manufacturer;

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }


    @Override
    public String toString() {
        return "MonitorRest{" + "refId=" + refId + ", description='" + description + '\'' + ", manufacturer='"
                + manufacturer + '\'' + '}';
    }
}