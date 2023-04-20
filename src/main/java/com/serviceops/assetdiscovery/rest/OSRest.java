package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.enums.Architecture;
import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.OS} entity
 */
public class OSRest extends AssetBaseRest implements Serializable {
    private String osName;
    private String osVersion;
    private Architecture architecture;
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

    public Architecture getOsArchitecture() {
        return architecture;
    }

    public void setOsArchitecture(Architecture architecture) {
        this.architecture = architecture;
    }

    public Long getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(Long installedDate) {
        this.installedDate = installedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        OSRest osRest = (OSRest) o;
        return Objects.equals(osName, osRest.osName) && Objects.equals(osVersion, osRest.osVersion)
                && architecture == osRest.architecture && Objects.equals(installedDate, osRest.installedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), osName, osVersion, architecture, installedDate);
    }

    @Override
    public String toString() {
        return "OSRest{" + "osName='" + osName + '\'' + ", osVersion='" + osVersion + '\'' + ", architecture="
                + architecture + ", installedDate=" + installedDate + '}';
    }
}



