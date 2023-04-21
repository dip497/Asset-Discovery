package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.NetworkAdapterRest;

import java.util.List;

public interface NetworkAdapterService {
    void save(long refId);

    List<NetworkAdapterRest> findAllByRefId(long refId);

    NetworkAdapterRest updateById(NetworkAdapterRest networkAdapterRest, long refId,long id);

    boolean deleteById(long refId, long id);
}
