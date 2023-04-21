package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.rest.BiosRest;

import java.util.List;

public interface BiosService {
    void save(AssetRest assetRest);

    List<BiosRest> findByRefId(long refId);

    BiosRest updateByRefId(long refId, BiosRest biosRest);

    boolean deleteByRefId(long refId);
}
