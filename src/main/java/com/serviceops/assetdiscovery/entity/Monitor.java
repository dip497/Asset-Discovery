package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.sql.Date;
import java.util.Objects;

@Entity
public class Monitor extends AssetBase {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Monitor monitor = (Monitor) o;

        if (getName() != null ? !getName().equals(monitor.getName()) : monitor.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(monitor.getDescription()) : monitor.getDescription() != null)
            return false;
        if (getScreenHeight() != null ? !getScreenHeight().equals(monitor.getScreenHeight()) : monitor.getScreenHeight() != null)
            return false;
        return getScreenWidth() != null ? getScreenWidth().equals(monitor.getScreenWidth()) : monitor.getScreenWidth() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getScreenHeight() != null ? getScreenHeight().hashCode() : 0);
        result = 31 * result + (getScreenWidth() != null ? getScreenWidth().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", screenHeight='" + screenHeight + '\'' +
                ", screenWidth='" + screenWidth + '\'' +
                '}';
    }
}
