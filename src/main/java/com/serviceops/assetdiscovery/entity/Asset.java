package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AuditBase;
import jakarta.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Asset extends AuditBase {
    private String hostName;
    private String domainName;
    private String ipAddress;
    private String assetType;
    private String serialNumber;
    private String macAddress;
    private String subNetMask;
    private String lastLoggedUser;

    public String getLastLoggedUser() {
        return lastLoggedUser;
    }

    public void setLastLoggedUser(String lastLoggedUser) {
        this.lastLoggedUser = lastLoggedUser;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getSubNetMask() {
        return subNetMask;
    }

    public void setSubNetMask(String subNetMask) {
        this.subNetMask = subNetMask;
    }

}
