package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.rest.BiosRest;
import com.serviceops.assetdiscovery.service.impl.BiosServiceImpl;

public interface BiosService {

    void save(AssetRest assetRest);

    BiosRest findByRefId(Long refId);

    void deleteByRefId(Long refId);

    void update(BiosRest biosRest);
}
