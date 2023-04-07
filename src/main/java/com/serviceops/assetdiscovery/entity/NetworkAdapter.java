package com.serviceops.assetdiscovery.entity;

import jakarta.persistence.Entity;

import java.util.Date;
@Entity
public class NetworkAdapter extends ExternalAssetBase {
    private String macAddress;

    private String[] ipAddress;
    private String dnsDomain;
    private String dnsHostName;
    private String dnsServerSearchOrders;
    private boolean dhcpEnable;
    private Date dhcpLeaseObtained;
    private Date dhcpLeaseExpires;
    private String dhcpServer;
    private String[] defualtIpGateway;
    private String[] ipSubnet;
    private String connectionStatus;



}
