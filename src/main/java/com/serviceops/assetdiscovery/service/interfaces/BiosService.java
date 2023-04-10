package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.rest.BiosRest;

public interface BiosService {

    public void save(AssetRest assetRest);

    public BiosRest findByRefId(Long refId);

}
