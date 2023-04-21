package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.AllAssetRest;
import com.serviceops.assetdiscovery.rest.AssetRest;

import java.util.Map;

public interface AssetService {
    AssetRest save();

    AssetRest findById(long id);

    AllAssetRest findPaginatedAssetData(int pageNo, int pageSize, String sortBy, String sortDir);

    AssetRest updateById(long id, Map<String, Object> fields);

    boolean deleteById(long id);

}
