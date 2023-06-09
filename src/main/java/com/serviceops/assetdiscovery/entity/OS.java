package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AuditBase;
import com.serviceops.assetdiscovery.entity.enums.Architecture;
import jakarta.persistence.Entity;

@Entity
public class OS extends AuditBase {
    private long refId;
    private String osName;
    private String osVersion;
    private Architecture architecture;
    private long installedDate;

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public Architecture getArchitecture() {
        return architecture;
    }

    public void setArchitecture(Architecture architecture) {
        this.architecture = architecture;
    }

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

    public long getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(long installedDate) {
        this.installedDate = installedDate;
    }


}
