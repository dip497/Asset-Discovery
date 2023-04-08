package com.serviceops.assetdiscovery.rest;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.OS} entity
 */
public class OSRest extends ExternalAssetBaseRest implements Serializable {
    private String osName;
    private String osVersion;
    private String osArchitecture;
    private String licenseKey;
    private String activationStatus;
    private Date installedDate;



    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getOsArchitecture() {
        return osArchitecture;
    }

    public void setOsArchitecture(String osArchitecture) {
        this.osArchitecture = osArchitecture;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public String getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(String activationStatus) {
        this.activationStatus = activationStatus;
    }

    public Date getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OSRest entity = (OSRest) o;
        return Objects.equals(this.osName, entity.osName) &&
                Objects.equals(this.osVersion, entity.osVersion) &&
                Objects.equals(this.osArchitecture, entity.osArchitecture) &&
                Objects.equals(this.licenseKey, entity.licenseKey) &&
                Objects.equals(this.activationStatus, entity.activationStatus) &&
                Objects.equals(this.installedDate, entity.installedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(osName, osVersion, osArchitecture, licenseKey, activationStatus, installedDate);
    }

    @Override
    public String toString() {
        return "OSRest{" +
                "osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", osArchitecture='" + osArchitecture + '\'' +
                ", licenseKey='" + licenseKey + '\'' +
                ", activationStatus='" + activationStatus + '\'' +
                ", installedDate=" + installedDate +
                "} " + super.toString();
    }
}