package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.rest.BiosRest;

import java.util.List;

public interface BiosService {

    void save(AssetRest assetRest);

    List<BiosRest> findByRefId(long refId);

    void deleteByRefId(long refId);

    void update(long refId, BiosRest biosRest);

}
