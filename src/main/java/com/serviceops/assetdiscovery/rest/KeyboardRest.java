package com.serviceops.assetdiscovery.rest;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Keyboard} entity
 */
public class KeyboardRest extends AssetBaseRest implements Serializable {
    private  String name;
    private  String description;
    private  Date installedDate;
    private  String pnpDeviceId;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getInstalledDate() {
        return installedDate;
    }

    public String getPnpDeviceId() {
        return pnpDeviceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
    }

    public void setPnpDeviceId(String pnpDeviceId) {
        this.pnpDeviceId = pnpDeviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyboardRest entity = (KeyboardRest) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.installedDate, entity.installedDate) &&
                Objects.equals(this.pnpDeviceId, entity.pnpDeviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, installedDate, pnpDeviceId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "installedDate = " + installedDate + ", " +
                "pnpDeviceId = " + pnpDeviceId + ")";
    }
}