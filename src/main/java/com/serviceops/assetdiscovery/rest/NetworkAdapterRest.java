package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.NetworkAdapter;
import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rest for the {@link NetworkAdapter} entity
 */
public class NetworkAdapterRest extends AssetBaseRest implements Serializable {
    private String description;
    @Pattern(regexp ="^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$\n")
    private String macAddress;
    @Pattern(regexp = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$\n")
    private String ipAddress;
    private String ipSubnet;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NetworkAdapterRest that = (NetworkAdapterRest) o;
        return Objects.equals(macAddress, that.macAddress) && Objects.equals(description, that.description) && Objects.equals(ipAddress, that.ipAddress) && Objects.equals(ipSubnet, that.ipSubnet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), macAddress, description, ipAddress, ipSubnet);
    }

    @Override
    public String toString() {
        return "NetworkAdapterRest{" + "macAddress='" + macAddress + '\'' + ", description='" + description + '\'' + ", ipAddress='" + ipAddress + '\'' + ", ipSubnet='" + ipSubnet + '\'' + '}';
    }
}