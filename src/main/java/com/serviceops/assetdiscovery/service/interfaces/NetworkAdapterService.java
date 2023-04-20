package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.entity.NetworkAdapter;
import com.serviceops.assetdiscovery.rest.NetworkAdapterRest;

import java.util.List;

public interface NetworkAdapterService {
    void save(long refId);

    void delete(long refId, long id);

    NetworkAdapterRest update(NetworkAdapterRest networkAdapterRest, long id, long refId);

    List<NetworkAdapterRest> findByRefId(Long refId);
}
