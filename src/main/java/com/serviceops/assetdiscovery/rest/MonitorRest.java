package com.serviceops.assetdiscovery.rest;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Monitor} entity
 */
public class MonitorRest extends AssetBaseRest implements Serializable {
    private  String monitorType;
    private  String description;
    private  float size;
    private  Date installedDate;
    private  int screenHeight;
    private  int screenWidth;
    private  int weekOfManufacture;
    private  int yearOfManufacture;
    private  String pnpDeviceId;



    public String getMonitorType() {
        return monitorType;
    }

    public String getDescription() {
        return description;
    }

    public float getSize() {
        return size;
    }

    public Date getInstalledDate() {
        return installedDate;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getWeekOfManufacture() {
        return weekOfManufacture;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public String getPnpDeviceId() {
        return pnpDeviceId;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setWeekOfManufacture(int weekOfManufacture) {
        this.weekOfManufacture = weekOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public void setPnpDeviceId(String pnpDeviceId) {
        this.pnpDeviceId = pnpDeviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonitorRest entity = (MonitorRest) o;
        return Objects.equals(this.monitorType, entity.monitorType) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.size, entity.size) &&
                Objects.equals(this.installedDate, entity.installedDate) &&
                Objects.equals(this.screenHeight, entity.screenHeight) &&
                Objects.equals(this.screenWidth, entity.screenWidth) &&
                Objects.equals(this.weekOfManufacture, entity.weekOfManufacture) &&
                Objects.equals(this.yearOfManufacture, entity.yearOfManufacture) &&
                Objects.equals(this.pnpDeviceId, entity.pnpDeviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monitorType, description, size, installedDate, screenHeight, screenWidth, weekOfManufacture, yearOfManufacture, pnpDeviceId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "monitorType = " + monitorType + ", " +
                "description = " + description + ", " +
                "size = " + size + ", " +
                "installedDate = " + installedDate + ", " +
                "screenHeight = " + screenHeight + ", " +
                "screenWidth = " + screenWidth + ", " +
                "weekOfManufacture = " + weekOfManufacture + ", " +
                "yearOfManufacture = " + yearOfManufacture + ", " +
                "pnpDeviceId = " + pnpDeviceId + ")";
    }
}