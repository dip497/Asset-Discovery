package com.serviceops.assetdiscovery.entity;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import jakarta.persistence.Entity;

import java.util.Date;
@Entity
public class NetworkAdapter extends AssetBase {
    private String macAddress;
    private String description;

    private String ipAddress;
    private String dnsDomain;
    private String dnsHostName;
    private String dnsServerSearchOrders;
    private boolean dhcpEnable;
    private Date dhcpLeaseObtained;
    private Date dhcpLeaseExpires;
    private String dhcpServer;
    private String defualtIpGateway;
    private String ipSubnet;
    private String connectionStatus;

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

    public String getDnsDomain() {
        return dnsDomain;
    }

    public void setDnsDomain(String dnsDomain) {
        this.dnsDomain = dnsDomain;
    }

    public String getDnsHostName() {
        return dnsHostName;
    }

    public void setDnsHostName(String dnsHostName) {
        this.dnsHostName = dnsHostName;
    }

    public String getDnsServerSearchOrders() {
        return dnsServerSearchOrders;
    }

    public void setDnsServerSearchOrders(String dnsServerSearchOrders) {
        this.dnsServerSearchOrders = dnsServerSearchOrders;
    }

    public boolean isDhcpEnable() {
        return dhcpEnable;
    }

    public void setDhcpEnable(boolean dhcpEnable) {
        this.dhcpEnable = dhcpEnable;
    }

    public Date getDhcpLeaseObtained() {
        return dhcpLeaseObtained;
    }

    public void setDhcpLeaseObtained(Date dhcpLeaseObtained) {
        this.dhcpLeaseObtained = dhcpLeaseObtained;
    }

    public Date getDhcpLeaseExpires() {
        return dhcpLeaseExpires;
    }

    public void setDhcpLeaseExpires(Date dhcpLeaseExpires) {
        this.dhcpLeaseExpires = dhcpLeaseExpires;
    }

    public String getDhcpServer() {
        return dhcpServer;
    }

    public void setDhcpServer(String dhcpServer) {
        this.dhcpServer = dhcpServer;
    }

    public String getDefualtIpGateway() {
        return defualtIpGateway;
    }

    public void setDefualtIpGateway(String defualtIpGateway) {
        this.defualtIpGateway = defualtIpGateway;
    }

    public String getIpSubnet() {
        return ipSubnet;
    }

    public void setIpSubnet(String ipSubnet) {
        this.ipSubnet = ipSubnet;
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetworkAdapter that = (NetworkAdapter) o;

        if (isDhcpEnable() != that.isDhcpEnable()) return false;
        if (getMacAddress() != null ? !getMacAddress().equals(that.getMacAddress()) : that.getMacAddress() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getIpAddress() != null ? !getIpAddress().equals(that.getIpAddress()) : that.getIpAddress() != null)
            return false;
        if (getDnsDomain() != null ? !getDnsDomain().equals(that.getDnsDomain()) : that.getDnsDomain() != null)
            return false;
        if (getDnsHostName() != null ? !getDnsHostName().equals(that.getDnsHostName()) : that.getDnsHostName() != null)
            return false;
        if (getDnsServerSearchOrders() != null ? !getDnsServerSearchOrders().equals(that.getDnsServerSearchOrders()) : that.getDnsServerSearchOrders() != null)
            return false;
        if (getDhcpLeaseObtained() != null ? !getDhcpLeaseObtained().equals(that.getDhcpLeaseObtained()) : that.getDhcpLeaseObtained() != null)
            return false;
        if (getDhcpLeaseExpires() != null ? !getDhcpLeaseExpires().equals(that.getDhcpLeaseExpires()) : that.getDhcpLeaseExpires() != null)
            return false;
        if (getDhcpServer() != null ? !getDhcpServer().equals(that.getDhcpServer()) : that.getDhcpServer() != null)
            return false;
        if (getDefualtIpGateway() != null ? !getDefualtIpGateway().equals(that.getDefualtIpGateway()) : that.getDefualtIpGateway() != null)
            return false;
        if (getIpSubnet() != null ? !getIpSubnet().equals(that.getIpSubnet()) : that.getIpSubnet() != null)
            return false;
        return getConnectionStatus() != null ? getConnectionStatus().equals(that.getConnectionStatus()) : that.getConnectionStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = getMacAddress() != null ? getMacAddress().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getIpAddress() != null ? getIpAddress().hashCode() : 0);
        result = 31 * result + (getDnsDomain() != null ? getDnsDomain().hashCode() : 0);
        result = 31 * result + (getDnsHostName() != null ? getDnsHostName().hashCode() : 0);
        result = 31 * result + (getDnsServerSearchOrders() != null ? getDnsServerSearchOrders().hashCode() : 0);
        result = 31 * result + (isDhcpEnable() ? 1 : 0);
        result = 31 * result + (getDhcpLeaseObtained() != null ? getDhcpLeaseObtained().hashCode() : 0);
        result = 31 * result + (getDhcpLeaseExpires() != null ? getDhcpLeaseExpires().hashCode() : 0);
        result = 31 * result + (getDhcpServer() != null ? getDhcpServer().hashCode() : 0);
        result = 31 * result + (getDefualtIpGateway() != null ? getDefualtIpGateway().hashCode() : 0);
        result = 31 * result + (getIpSubnet() != null ? getIpSubnet().hashCode() : 0);
        result = 31 * result + (getConnectionStatus() != null ? getConnectionStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NetworkAdapter{" +
                "macAddress='" + macAddress + '\'' +
                ", description='" + description + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", dnsDomain='" + dnsDomain + '\'' +
                ", dnsHostName='" + dnsHostName + '\'' +
                ", dnsServerSearchOrders='" + dnsServerSearchOrders + '\'' +
                ", dhcpEnable=" + dhcpEnable +
                ", dhcpLeaseObtained=" + dhcpLeaseObtained +
                ", dhcpLeaseExpires=" + dhcpLeaseExpires +
                ", dhcpServer='" + dhcpServer + '\'' +
                ", defualtIpGateway='" + defualtIpGateway + '\'' +
                ", ipSubnet='" + ipSubnet + '\'' +
                ", connectionStatus='" + connectionStatus + '\'' +
                '}';
    }
}
