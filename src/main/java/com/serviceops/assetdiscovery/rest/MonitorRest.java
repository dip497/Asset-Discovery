package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Monitor} entity
 */
public class MonitorRest extends AssetBaseRest implements Serializable {

    private String name;
    private String description;
    private String screenHeight;
    private String screenWidth;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        MonitorRest that = (MonitorRest) o;
        return Objects.equals(description, that.description) && Objects.equals(name, that.name)
                && Objects.equals(screenHeight, that.screenHeight) && Objects.equals(screenWidth,
                that.screenWidth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, screenHeight, screenWidth);
    }

    @Override
    public String toString() {
        return "MonitorRest{" + ", name='" + name + '\'' + ", description='" + description + '\''
                + ", screenHeight='" + screenHeight + '\'' + ", screenWidth='" + screenWidth + '\'' + '}';
    }
}