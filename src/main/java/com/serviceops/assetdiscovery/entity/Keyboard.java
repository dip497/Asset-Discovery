package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

import java.sql.Date;
@Entity
public class Keyboard extends AssetBase {
    private String name;
    private String description;
    private Date installedDate;
    private String pnpDeviceId;

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

    public Date getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
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

        Keyboard keyboard = (Keyboard) o;

        if (getName() != null ? !getName().equals(keyboard.getName()) : keyboard.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(keyboard.getDescription()) : keyboard.getDescription() != null)
            return false;
        if (getInstalledDate() != null ? !getInstalledDate().equals(keyboard.getInstalledDate()) : keyboard.getInstalledDate() != null)
            return false;
        return getPnpDeviceId() != null ? getPnpDeviceId().equals(keyboard.getPnpDeviceId()) : keyboard.getPnpDeviceId() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getInstalledDate() != null ? getInstalledDate().hashCode() : 0);
        result = 31 * result + (getPnpDeviceId() != null ? getPnpDeviceId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Keyboard{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", installedDate=" + installedDate +
                ", pnpDeviceId='" + pnpDeviceId + '\'' +
                '}';
    }
}
