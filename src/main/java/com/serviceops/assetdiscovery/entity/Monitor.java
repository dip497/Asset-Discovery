package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.SingleBase;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class Monitor extends SingleBase {

    private long refId;
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
        return "Monitor{" + "refId=" + refId + ", description='" + description + '\'' + ", screenHeight='"
                + screenHeight + '\'' + ", screenWidth='" + screenWidth + '\'' + ", manufacturer='"
                + manufacturer + '\'' + '}';
    }
}
