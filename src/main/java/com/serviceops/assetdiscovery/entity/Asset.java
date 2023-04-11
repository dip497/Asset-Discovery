package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.SingleBase;
import jakarta.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;

import java.net.InetAddress;
@Entity
@DynamicUpdate
public class Asset extends SingleBase {
    private String hostName;
    private String domainName;
    private String ipAddress;
    private String assetType;
    private String serialNumber;
    private String macAddress;
    private String subNetMask;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Asset asset = (Asset) o;

        if (getHostName() != null ? !getHostName().equals(asset.getHostName()) : asset.getHostName() != null)
            return false;
        if (getDomainName() != null ? !getDomainName().equals(asset.getDomainName()) : asset.getDomainName() != null)
            return false;
        if (getIpAddress() != null ? !getIpAddress().equals(asset.getIpAddress()) : asset.getIpAddress() != null)
            return false;
        if (getAssetType() != null ? !getAssetType().equals(asset.getAssetType()) : asset.getAssetType() != null)
            return false;
        if (getSerialNumber() != null ? !getSerialNumber().equals(asset.getSerialNumber()) : asset.getSerialNumber() != null)
            return false;
        if (getMacAddress() != null ? !getMacAddress().equals(asset.getMacAddress()) : asset.getMacAddress() != null)
            return false;
        return getSubNetMask() != null ? getSubNetMask().equals(asset.getSubNetMask()) : asset.getSubNetMask() == null;
    }

    @Override
    public int hashCode() {
        int result = getHostName() != null ? getHostName().hashCode() : 0;
        result = 31 * result + (getDomainName() != null ? getDomainName().hashCode() : 0);
        result = 31 * result + (getIpAddress() != null ? getIpAddress().hashCode() : 0);
        result = 31 * result + (getAssetType() != null ? getAssetType().hashCode() : 0);
        result = 31 * result + (getSerialNumber() != null ? getSerialNumber().hashCode() : 0);
        result = 31 * result + (getMacAddress() != null ? getMacAddress().hashCode() : 0);
        result = 31 * result + (getSubNetMask() != null ? getSubNetMask().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "hostName='" + hostName + '\'' +
                ", domainName='" + domainName + '\'' +
                ", ipAddress=" + ipAddress +
                ", assetType='" + assetType + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", subNetMask='" + subNetMask + '\'' +
                '}';
    }
}
