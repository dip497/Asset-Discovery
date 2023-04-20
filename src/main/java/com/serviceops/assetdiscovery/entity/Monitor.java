package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.sql.Date;
import java.util.Objects;

@Entity
public class Monitor extends AssetBase {

    private String description;
    private String screenHeight;
    private String screenWidth;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Monitor monitor = (Monitor) o;
        return Objects.equals(description, monitor.description) && Objects.equals(screenHeight, monitor.screenHeight) && Objects.equals(screenWidth, monitor.screenWidth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, screenHeight, screenWidth);
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "description='" + description + '\'' +
                ", screenHeight='" + screenHeight + '\'' +
                ", screenWidth='" + screenWidth + '\'' +
                '}';
    }
}
