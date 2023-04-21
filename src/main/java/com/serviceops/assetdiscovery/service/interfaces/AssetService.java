package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.AllAssetRest;
import com.serviceops.assetdiscovery.rest.AssetRest;

import java.util.Map;

public interface AssetService {
    AssetRest save();

    AssetRest findById(long id);

    AllAssetRest findPaginatedData(int pageNo, int pageSize, String sortBy, String sortDir);

    boolean deleteById(long id);

    AssetRest update(long id, Map<String, Object> fields);

}
