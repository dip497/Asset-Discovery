package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import com.serviceops.assetdiscovery.entity.enums.Architecture;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class OS extends AssetBase {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OS os = (OS) o;
        return Objects.equals(osName, os.osName) && Objects.equals(osVersion, os.osVersion) && architecture == os.architecture && Objects.equals(installedDate, os.installedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), osName, osVersion, architecture, installedDate);
    }

    @Override
    public String toString() {
        return "OS{" +
                "osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", architecture=" + architecture +
                ", installedDate=" + installedDate +
                '}';
    }

}
