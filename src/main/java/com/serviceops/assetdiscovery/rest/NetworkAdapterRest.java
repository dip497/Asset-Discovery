package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.NetworkAdapter;
import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * A Rest for the {@link NetworkAdapter} entity
 */
public class NetworkAdapterRest extends AssetBaseRest implements Serializable {
    private   String macAddress;
    private   String description;
    private   String ipAddress;
    private   String dnsDomain;
    private   String dnsHostName;
    private   String dnsServerSearchOrders;
    private   boolean dhcpEnable;
    private   Date dhcpLeaseObtained;
    private   Date dhcpLeaseExpires;
    private   String dhcpServer;
    private   String defaultIpGateway;
    private   String ipSubnet;
    private   String connectionStatus;

    public String getMacAddress() {
        return macAddress;
    }

    public String getDescription() {
        return description;
    }



    public String getDnsDomain() {
        return dnsDomain;
    }

    public String getDnsHostName() {
        return dnsHostName;
    }

    public String getDnsServerSearchOrders() {
        return dnsServerSearchOrders;
    }

    public boolean getDhcpEnable() {
        return dhcpEnable;
    }

    public Date getDhcpLeaseObtained() {
        return dhcpLeaseObtained;
    }

    public Date getDhcpLeaseExpires() {
        return dhcpLeaseExpires;
    }

    public String getDhcpServer() {
        return dhcpServer;
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDnsDomain(String dnsDomain) {
        this.dnsDomain = dnsDomain;
    }

    public void setDnsHostName(String dnsHostName) {
        this.dnsHostName = dnsHostName;
    }

    public void setDnsServerSearchOrders(String dnsServerSearchOrders) {
        this.dnsServerSearchOrders = dnsServerSearchOrders;
    }

    public void setDhcpEnable(boolean dhcpEnable) {
        this.dhcpEnable = dhcpEnable;
    }

    public void setDhcpLeaseObtained(Date dhcpLeaseObtained) {
        this.dhcpLeaseObtained = dhcpLeaseObtained;
    }

    public void setDhcpLeaseExpires(Date dhcpLeaseExpires) {
        this.dhcpLeaseExpires = dhcpLeaseExpires;
    }

    public void setDhcpServer(String dhcpServer) {
        this.dhcpServer = dhcpServer;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public boolean isDhcpEnable() {
        return dhcpEnable;
    }

    public String getDefaultIpGateway() {
        return defaultIpGateway;
    }

    public void setDefaultIpGateway(String defaultIpGateway) {
        this.defaultIpGateway = defaultIpGateway;
    }

    public String getIpSubnet() {
        return ipSubnet;
    }

    public void setIpSubnet(String ipSubnet) {
        this.ipSubnet = ipSubnet;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NetworkAdapterRest that = (NetworkAdapterRest) o;
        return dhcpEnable == that.dhcpEnable && Objects.equals(macAddress, that.macAddress) && Objects.equals(description, that.description) && Objects.equals(ipAddress, that.ipAddress) && Objects.equals(dnsDomain, that.dnsDomain) && Objects.equals(dnsHostName, that.dnsHostName) && Objects.equals(dnsServerSearchOrders, that.dnsServerSearchOrders) && Objects.equals(dhcpLeaseObtained, that.dhcpLeaseObtained) && Objects.equals(dhcpLeaseExpires, that.dhcpLeaseExpires) && Objects.equals(dhcpServer, that.dhcpServer) && Objects.equals(defaultIpGateway, that.defaultIpGateway) && Objects.equals(ipSubnet, that.ipSubnet) && Objects.equals(connectionStatus, that.connectionStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), macAddress, description, ipAddress, dnsDomain, dnsHostName, dnsServerSearchOrders, dhcpEnable, dhcpLeaseObtained, dhcpLeaseExpires, dhcpServer, defaultIpGateway, ipSubnet, connectionStatus);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "macAddress = " + macAddress + ", " +
                "description = " + description + ", " +
                "ipAddress = " + ipAddress + ", " +
                "dnsDomain = " + dnsDomain + ", " +
                "dnsHostName = " + dnsHostName + ", " +
                "dnsServerSearchOrders = " + dnsServerSearchOrders + ", " +
                "dhcpEnable = " + dhcpEnable + ", " +
                "dhcpLeaseObtained = " + dhcpLeaseObtained + ", " +
                "dhcpLeaseExpires = " + dhcpLeaseExpires + ", " +
                "dhcpServer = " + dhcpServer + ", " +
                "defualtIpGateway = " + defaultIpGateway + ", " +
                "ipSubnet = " + ipSubnet + ", " +
                "connectionStatus = " + connectionStatus + ")";
    }
}