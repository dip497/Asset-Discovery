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
        networkAdapter.setIpAddress(networkAdapterRest.getIpAddress());
        networkAdapter.setMacAddress(networkAdapterRest.getMacAddress());
        networkAdapter.setIpSubnet(networkAdapterRest.getIpSubnet());

        return networkAdapter;
    }

    public NetworkAdapterRest entityToRest() {
        super.entityToRest(networkAdapter);
        networkAdapterRest.setDescription(networkAdapter.getDescription());
        networkAdapterRest.setManufacturer(networkAdapter.getManufacturer());
        networkAdapterRest.setIpAddress(networkAdapter.getIpAddress());
        networkAdapterRest.setMacAddress(networkAdapter.getMacAddress());
        networkAdapterRest.setIpSubnet(networkAdapter.getIpSubnet());

        return networkAdapterRest;
    }
}
