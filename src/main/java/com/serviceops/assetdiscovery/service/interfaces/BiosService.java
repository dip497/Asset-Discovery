package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.rest.BiosRest;

import java.util.List;

public interface BiosService {

    void save(AssetRest assetRest);

    List<BiosRest> findByRefId(Long refId);

    void deleteByRefId(Long refId);

    void update(Long refId, BiosRest biosRest);

}
