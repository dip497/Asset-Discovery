package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

@Entity
public class OS extends AssetBase {
    private String osName;
    private String osVersion;
    private String osArchitecture;
    private String licenseKey;
    private String activationStatus;
    private Long installedDate;


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

    public Long getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(Long installedDate) {
        this.installedDate = installedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OS os = (OS) o;

        if (getOsName() != null ? !getOsName().equals(os.getOsName()) : os.getOsName() != null) return false;
        if (getOsVersion() != null ? !getOsVersion().equals(os.getOsVersion()) : os.getOsVersion() != null)
            return false;
        if (getOsArchitecture() != null ? !getOsArchitecture().equals(os.getOsArchitecture()) : os.getOsArchitecture() != null)
            return false;
        if (getLicenseKey() != null ? !getLicenseKey().equals(os.getLicenseKey()) : os.getLicenseKey() != null)
            return false;
        if (getActivationStatus() != null ? !getActivationStatus().equals(os.getActivationStatus()) : os.getActivationStatus() != null)
            return false;
        return getInstalledDate() != null ? getInstalledDate().equals(os.getInstalledDate()) : os.getInstalledDate() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getOsName() != null ? getOsName().hashCode() : 0);
        result = 31 * result + (getOsVersion() != null ? getOsVersion().hashCode() : 0);
        result = 31 * result + (getOsArchitecture() != null ? getOsArchitecture().hashCode() : 0);
        result = 31 * result + (getLicenseKey() != null ? getLicenseKey().hashCode() : 0);
        result = 31 * result + (getActivationStatus() != null ? getActivationStatus().hashCode() : 0);
        result = 31 * result + (getInstalledDate() != null ? getInstalledDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OS{" +
                "osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", osArchitecture='" + osArchitecture + '\'' +
                ", licenseKey='" + licenseKey + '\'' +
                ", activationStatus='" + activationStatus + '\'' +
                ", installedDate=" + installedDate +
                "} " + super.toString();
    }
}
