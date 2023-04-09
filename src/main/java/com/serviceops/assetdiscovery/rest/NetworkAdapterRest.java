package com.serviceops.assetdiscovery.rest;

import com.serviceops.assetdiscovery.entity.NetworkAdapter;
import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A Rest for the {@link NetworkAdapter} entity
 */
public class NetworkAdapterRest extends AssetBaseRest implements Serializable {
    private   String macAddress;
    private   String description;
    private   String[] ipAddress;
    private   String dnsDomain;
    private   String dnsHostName;
    private   String dnsServerSearchOrders;
    private   boolean dhcpEnable;
    private   Date dhcpLeaseObtained;
    private   Date dhcpLeaseExpires;
    private   String dhcpServer;
    private   String[] defualtIpGateway;
    private   String[] ipSubnet;
    private   String connectionStatus;

    public String getMacAddress() {
        return macAddress;
    }

    public String getDescription() {
        return description;
    }

    public String[] getIpAddress() {
        return ipAddress;
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

    public String[] getDefualtIpGateway() {
        return defualtIpGateway;
    }

    public String[] getIpSubnet() {
        return ipSubnet;
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

    public void setIpAddress(String[] ipAddress) {
        this.ipAddress = ipAddress;
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

    public void setDefualtIpGateway(String[] defualtIpGateway) {
        this.defualtIpGateway = defualtIpGateway;
    }

    public void setIpSubnet(String[] ipSubnet) {
        this.ipSubnet = ipSubnet;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetworkAdapterRest entity = (NetworkAdapterRest) o;
        return Objects.equals(this.macAddress, entity.macAddress) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.ipAddress, entity.ipAddress) &&
                Objects.equals(this.dnsDomain, entity.dnsDomain) &&
                Objects.equals(this.dnsHostName, entity.dnsHostName) &&
                Objects.equals(this.dnsServerSearchOrders, entity.dnsServerSearchOrders) &&
                Objects.equals(this.dhcpEnable, entity.dhcpEnable) &&
                Objects.equals(this.dhcpLeaseObtained, entity.dhcpLeaseObtained) &&
                Objects.equals(this.dhcpLeaseExpires, entity.dhcpLeaseExpires) &&
                Objects.equals(this.dhcpServer, entity.dhcpServer) &&
                Objects.equals(this.defualtIpGateway, entity.defualtIpGateway) &&
                Objects.equals(this.ipSubnet, entity.ipSubnet) &&
                Objects.equals(this.connectionStatus, entity.connectionStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(macAddress, description, ipAddress, dnsDomain, dnsHostName, dnsServerSearchOrders, dhcpEnable, dhcpLeaseObtained, dhcpLeaseExpires, dhcpServer, defualtIpGateway, ipSubnet, connectionStatus);
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
                "defualtIpGateway = " + defualtIpGateway + ", " +
                "ipSubnet = " + ipSubnet + ", " +
                "connectionStatus = " + connectionStatus + ")";
    }
}