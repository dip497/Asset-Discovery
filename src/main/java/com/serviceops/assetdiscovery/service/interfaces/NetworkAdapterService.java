package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.NetworkAdapterRest;

import java.util.List;

public interface NetworkAdapterService {
    void save(Long refId);

    void delete(Long refId, Long id);

    void update(NetworkAdapterRest networkAdapterRest, long id, long refId);

    List<NetworkAdapterRest> findByRefId(Long refId);
}
