package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.NetworkAdapterRest;

import java.util.List;

public interface NetworkAdapterService {
    public void save(Long refId);
    public void delete(Long refId, Long id);
    public void update(NetworkAdapterRest networkAdapterRest, long id, long refId);
    public List<NetworkAdapterRest> findByRefId(Long refId);
}
