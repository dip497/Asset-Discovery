package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.NetworkAdapterRest;

import java.util.List;

public interface NetworkAdapterService {
    void save(long refId);

    boolean deleteById(long refId, long id);

    NetworkAdapterRest updateById(NetworkAdapterRest networkAdapterRest, long refId,long id);

    List<NetworkAdapterRest> findAllByRefId(Long refId);
}
