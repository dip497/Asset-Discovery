package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.NetworkAdapter;
import com.serviceops.assetdiscovery.rest.NetworkAdapterRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class NetworkAdapterOps extends SingleBaseOps<NetworkAdapter, NetworkAdapterRest> {

    @Override
    public NetworkAdapter restToEntity(NetworkAdapter networkAdapter, NetworkAdapterRest networkAdapterRest) {
        super.restToEntity(networkAdapter, networkAdapterRest);
        networkAdapter.setRefId(networkAdapterRest.getRefId());
        networkAdapter.setDescription(networkAdapterRest.getDescription());
        networkAdapter.setManufacturer(networkAdapterRest.getManufacturer());
        networkAdapter.setIpAddress(networkAdapterRest.getIpAddress());
        networkAdapter.setMacAddress(networkAdapterRest.getMacAddress());
        networkAdapter.setIpSubnet(networkAdapterRest.getIpSubnet());

        return networkAdapter;
    }

    @Override
    public NetworkAdapterRest entityToRest(NetworkAdapter networkAdapter, NetworkAdapterRest networkAdapterRest) {
        super.entityToRest(networkAdapter, networkAdapterRest);
        networkAdapterRest.setRefId(networkAdapter.getRefId());
        networkAdapterRest.setDescription(networkAdapter.getDescription());
        networkAdapterRest.setManufacturer(networkAdapter.getManufacturer());
        networkAdapterRest.setIpAddress(networkAdapter.getIpAddress());
        networkAdapterRest.setMacAddress(networkAdapter.getMacAddress());
        networkAdapterRest.setIpSubnet(networkAdapter.getIpSubnet());

        return networkAdapterRest;
    }
}
