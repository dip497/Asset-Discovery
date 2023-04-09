package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.rest.base.SingleBaseRest;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Objects;

/**
 * A Rest for the {@link com.serviceops.assetdiscovery.entity.Asset} entity
 */
public class AssetRest extends SingleBaseRest implements Serializable {
    private String hostName;
    private String domainName;
    private InetAddress ipAddress;
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

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
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
        AssetRest entity = (AssetRest) o;
        return Objects.equals(this.hostName, entity.hostName) &&
                Objects.equals(this.domainName, entity.domainName) &&
                Objects.equals(this.ipAddress, entity.ipAddress) &&
                Objects.equals(this.assetType, entity.assetType) &&
                Objects.equals(this.serialNumber, entity.serialNumber) &&
                Objects.equals(this.macAddress, entity.macAddress) &&
                Objects.equals(this.subNetMask, entity.subNetMask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostName, domainName, ipAddress, assetType, serialNumber, macAddress, subNetMask);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "hostName = " + hostName + ", " +
                "domainName = " + domainName + ", " +
                "ipAddress = " + ipAddress + ", " +
                "assetType = " + assetType + ", " +
                "serialNumber = " + serialNumber + ", " +
                "macAddress = " + macAddress + ", " +
                "subNetMask = " + subNetMask + ")";
    }
}