package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

@Entity
public class NetworkAdapter extends AssetBase {
    private long refId;
    private String manufacturer;
    private String macAddress;
    private String ipAddress;
    private String description;
    private String ipSubnet;

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpSubnet() {
        return ipSubnet;
    }

    public void setIpSubnet(String ipSubnet) {
        this.ipSubnet = ipSubnet;
    }

    @Override
    public long getRefId() {
        return refId;
    }

    @Override
    public void setRefId(long refId) {
        this.refId = refId;
    }
}
