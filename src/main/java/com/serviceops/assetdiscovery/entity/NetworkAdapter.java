package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class NetworkAdapter extends AssetBase {
    private String macAddress;
    private String ipAddress;
    private String description;
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
        NetworkAdapter that = (NetworkAdapter) o;
        return Objects.equals(macAddress, that.macAddress) && Objects.equals(description, that.description) && Objects.equals(ipAddress, that.ipAddress) && Objects.equals(ipSubnet, that.ipSubnet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), macAddress, description, ipAddress, ipSubnet);
    }

    @Override
    public String toString() {
        return "NetworkAdapter{" +
                "macAddress='" + macAddress + '\'' +
                ", description='" + description + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", ipSubnet='" + ipSubnet + '\'' +
                '}';
    }
}
