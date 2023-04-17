package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.NetworkAdapterRest;

import java.util.List;

public interface NetworkAdapterService {
    public void save(Long id);
    public void delete(Long id);
    public void update(NetworkAdapterRest networkAdapterRest);
    public List<NetworkAdapterRest> findByRefId(Long id);
}
