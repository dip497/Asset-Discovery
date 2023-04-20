package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.AllAssetRest;
import com.serviceops.assetdiscovery.rest.AssetRest;

import java.util.Map;

public interface AssetService {

    AssetRest save();

    AssetRest findByIpAddress(String ipAddress);

    AssetRest findById(long id);

    AllAssetRest findPaginatedData(int pageNo, int pageSize, String sortBy, String sortDir);

    int findTotalCount();

    void deleteById(long id);

    void update(long id, Map<String, Object> fields);

}
