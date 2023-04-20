package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.base.SingleBase;
import com.serviceops.assetdiscovery.rest.base.SingleBaseRest;

import java.io.Serializable;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Monitor} entity
 */
public class MonitorRest extends SingleBaseRest implements Serializable {

    private long refId;
    private String name;
    private String description;
    private String screenHeight;
    private String screenWidth;
    private String manufacturer;

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(String screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(String screenWidth) {
        this.screenWidth = screenWidth;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "MonitorRest{" + "refId=" + refId + ", name='" + name + '\'' + ", description='" + description
                + '\'' + ", screenHeight='" + screenHeight + '\'' + ", screenWidth='" + screenWidth + '\''
                + ", manufacturer='" + manufacturer + '\'' + '}';
    }
}