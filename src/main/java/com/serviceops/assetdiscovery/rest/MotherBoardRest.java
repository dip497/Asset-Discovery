package com.serviceops.assetdiscovery.rest;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.MotherBoard} entity
 */
public class MotherBoardRest implements Serializable {
    private  String version;
    private  Date installedDate;
    private  String partNumber;
    private  String primaryBusType;
    private  String secondaryBusType;

    public String getVersion() {
        return version;
    }

    public Date getInstalledDate() {
        return installedDate;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getPrimaryBusType() {
        return primaryBusType;
    }

    public String getSecondaryBusType() {
        return secondaryBusType;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public void setPrimaryBusType(String primaryBusType) {
        this.primaryBusType = primaryBusType;
    }

    public void setSecondaryBusType(String secondaryBusType) {
        this.secondaryBusType = secondaryBusType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotherBoardRest entity = (MotherBoardRest) o;
        return Objects.equals(this.version, entity.version) &&
                Objects.equals(this.installedDate, entity.installedDate) &&
                Objects.equals(this.partNumber, entity.partNumber) &&
                Objects.equals(this.primaryBusType, entity.primaryBusType) &&
                Objects.equals(this.secondaryBusType, entity.secondaryBusType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, installedDate, partNumber, primaryBusType, secondaryBusType);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "version = " + version + ", " +
                "installedDate = " + installedDate + ", " +
                "partNumber = " + partNumber + ", " +
                "primaryBusType = " + primaryBusType + ", " +
                "secondaryBusType = " + secondaryBusType + ")";
    }
}