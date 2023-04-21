package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.NetworkAdapter;
import com.serviceops.assetdiscovery.rest.base.AuditBaseRest;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * A Rest for the {@link NetworkAdapter} entity
 */
public class NetworkAdapterRest extends AuditBaseRest implements Serializable {
    private long refId;
    private String manufacturer;
    private String description;
    @Pattern(regexp = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$\n")
    private String macAddress;
    @Pattern(regexp = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$\n")
    private String ipAddress;
    private String ipSubnet;

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

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
}