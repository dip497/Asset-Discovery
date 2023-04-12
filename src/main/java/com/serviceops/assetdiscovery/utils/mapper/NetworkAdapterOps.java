package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.NetworkAdapter;
import com.serviceops.assetdiscovery.rest.NetworkAdapterRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

public class NetworkAdapterOps extends AssetBaseOps<NetworkAdapter, NetworkAdapterRest> {
    private final NetworkAdapter networkAdapter;
    private final NetworkAdapterRest networkAdapterRest;

    public NetworkAdapterOps(NetworkAdapter networkAdapter, NetworkAdapterRest networkAdapterRest) {
        super(networkAdapter, networkAdapterRest);
        this.networkAdapter = networkAdapter;
        this.networkAdapterRest = networkAdapterRest;
    }
    public NetworkAdapter restToEntity() {
        super.restToEntity(networkAdapterRest);
        networkAdapter.setDescription(networkAdapterRest.getDescription());
        networkAdapter.setManufacturer(networkAdapterRest.getManufacturer());
        networkAdapter.setConnectionStatus(networkAdapterRest.getConnectionStatus());
        networkAdapter.setIpAddress(networkAdapterRest.getIpAddress());
        networkAdapter.setMacAddress(networkAdapterRest.getMacAddress());
        networkAdapter.setDnsDomain(networkAdapterRest.getDnsDomain());
        networkAdapter.setDnsHostName(networkAdapterRest.getDnsHostName());
        networkAdapter.setDnsServerSearchOrders(networkAdapterRest.getDnsServerSearchOrders());
        networkAdapter.setDhcpEnable(networkAdapterRest.getDhcpEnable());
        networkAdapter.setDhcpLeaseObtained(networkAdapterRest.getDhcpLeaseObtained());
        networkAdapter.setDhcpLeaseExpires(networkAdapterRest.getDhcpLeaseExpires());
        networkAdapter.setDhcpServer(networkAdapterRest.getDhcpServer());
        networkAdapter.setDefaultIpGateway(networkAdapterRest.getDefaultIpGateway());
        networkAdapter.setIpSubnet(networkAdapterRest.getIpSubnet());

        return networkAdapter;
    }

    public NetworkAdapterRest entityToRest() {
        super.entityToRest(networkAdapter);
        networkAdapterRest.setDescription(networkAdapter.getDescription());
        networkAdapterRest.setManufacturer(networkAdapter.getManufacturer());
        networkAdapterRest.setConnectionStatus(networkAdapter.getConnectionStatus());
        networkAdapterRest.setIpAddress(networkAdapter.getIpAddress());
        networkAdapterRest.setMacAddress(networkAdapter.getMacAddress());
        networkAdapterRest.setDnsDomain(networkAdapter.getDnsDomain());
        networkAdapterRest.setDnsHostName(networkAdapter.getDnsHostName());
        networkAdapterRest.setDnsServerSearchOrders(networkAdapter.getDnsServerSearchOrders());
        networkAdapterRest.setDhcpEnable(networkAdapter.isDhcpEnable());
        networkAdapterRest.setDhcpLeaseObtained(networkAdapter.getDhcpLeaseObtained());
        networkAdapterRest.setDhcpLeaseExpires(networkAdapter.getDhcpLeaseExpires());
        networkAdapterRest.setDhcpServer(networkAdapter.getDhcpServer());
        networkAdapterRest.setDefaultIpGateway(networkAdapter.getDefaultIpGateway());
        networkAdapterRest.setIpSubnet(networkAdapter.getIpSubnet());

        return networkAdapterRest;
    }
}
