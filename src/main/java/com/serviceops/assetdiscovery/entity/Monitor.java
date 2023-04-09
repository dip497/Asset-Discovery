package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

import java.sql.Date;
@Entity
public class Monitor extends AssetBase {

    private String monitorType;
    private String description;
    private float size;

    private Date installedDate;
    private int screenHeight;
    private int screenWidth;
    private int weekOfManufacture;
    private int yearOfManufacture;
    private String pnpDeviceId;

    public String getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public Date getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getWeekOfManufacture() {
        return weekOfManufacture;
    }

    public void setWeekOfManufacture(int weekOfManufacture) {
        this.weekOfManufacture = weekOfManufacture;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getPnpDeviceId() {
        return pnpDeviceId;
    }

    public void setPnpDeviceId(String pnpDeviceId) {
        this.pnpDeviceId = pnpDeviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Monitor monitor = (Monitor) o;

        if (Float.compare(monitor.getSize(), getSize()) != 0) return false;
        if (getScreenHeight() != monitor.getScreenHeight()) return false;
        if (getScreenWidth() != monitor.getScreenWidth()) return false;
        if (getWeekOfManufacture() != monitor.getWeekOfManufacture()) return false;
        if (getYearOfManufacture() != monitor.getYearOfManufacture()) return false;
        if (getMonitorType() != null ? !getMonitorType().equals(monitor.getMonitorType()) : monitor.getMonitorType() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(monitor.getDescription()) : monitor.getDescription() != null)
            return false;
        if (getInstalledDate() != null ? !getInstalledDate().equals(monitor.getInstalledDate()) : monitor.getInstalledDate() != null)
            return false;
        return getPnpDeviceId() != null ? getPnpDeviceId().equals(monitor.getPnpDeviceId()) : monitor.getPnpDeviceId() == null;
    }

    @Override
    public int hashCode() {
        int result = getMonitorType() != null ? getMonitorType().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getSize() != +0.0f ? Float.floatToIntBits(getSize()) : 0);
        result = 31 * result + (getInstalledDate() != null ? getInstalledDate().hashCode() : 0);
        result = 31 * result + getScreenHeight();
        result = 31 * result + getScreenWidth();
        result = 31 * result + getWeekOfManufacture();
        result = 31 * result + getYearOfManufacture();
        result = 31 * result + (getPnpDeviceId() != null ? getPnpDeviceId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "monitorType='" + monitorType + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                ", installedDate=" + installedDate +
                ", screenHeight=" + screenHeight +
                ", screenWidth=" + screenWidth +
                ", weekOfManufacture=" + weekOfManufacture +
                ", yearOfManufacture=" + yearOfManufacture +
                ", pnpDeviceId='" + pnpDeviceId + '\'' +
                '}';
    }

}
