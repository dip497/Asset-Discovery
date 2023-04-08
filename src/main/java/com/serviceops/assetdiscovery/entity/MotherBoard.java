package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

import java.sql.Date;
@Entity
public class MotherBoard extends AssetBase {
    private String version;
    private Date installedDate;
    private String partNumber;
    private String primaryBusType;
    private String secondaryBusType;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPrimaryBusType() {
        return primaryBusType;
    }

    public void setPrimaryBusType(String primaryBusType) {
        this.primaryBusType = primaryBusType;
    }

    public String getSecondaryBusType() {
        return secondaryBusType;
    }

    public void setSecondaryBusType(String secondaryBusType) {
        this.secondaryBusType = secondaryBusType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MotherBoard that = (MotherBoard) o;

        if (getVersion() != null ? !getVersion().equals(that.getVersion()) : that.getVersion() != null) return false;
        if (getInstalledDate() != null ? !getInstalledDate().equals(that.getInstalledDate()) : that.getInstalledDate() != null)
            return false;
        if (getPartNumber() != null ? !getPartNumber().equals(that.getPartNumber()) : that.getPartNumber() != null)
            return false;
        if (getPrimaryBusType() != null ? !getPrimaryBusType().equals(that.getPrimaryBusType()) : that.getPrimaryBusType() != null)
            return false;
        return getSecondaryBusType() != null ? getSecondaryBusType().equals(that.getSecondaryBusType()) : that.getSecondaryBusType() == null;
    }

    @Override
    public int hashCode() {
        int result = getVersion() != null ? getVersion().hashCode() : 0;
        result = 31 * result + (getInstalledDate() != null ? getInstalledDate().hashCode() : 0);
        result = 31 * result + (getPartNumber() != null ? getPartNumber().hashCode() : 0);
        result = 31 * result + (getPrimaryBusType() != null ? getPrimaryBusType().hashCode() : 0);
        result = 31 * result + (getSecondaryBusType() != null ? getSecondaryBusType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MotherBoard{" +
                "version='" + version + '\'' +
                ", installedDate=" + installedDate +
                ", partNumber='" + partNumber + '\'' +
                ", primaryBusType='" + primaryBusType + '\'' +
                ", secondaryBusType='" + secondaryBusType + '\'' +
                '}';
    }
}
