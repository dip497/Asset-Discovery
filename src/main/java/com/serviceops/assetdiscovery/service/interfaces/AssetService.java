package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.utils.AllAssetResponse;

import java.util.List;
import java.util.Map;

public interface AssetService {

    public AssetRest save();

    public AssetRest findByIpAddress(String ipAddress);

    public AssetRest findById(Long id);

    public AllAssetResponse findPaginatedData(int pageNo, int pageSize, String sortBy, String sortDir);

    public int findTotalCount();

    public void deleteById(Long id);

    public void update(Long id, Map<String, Object> fields);

}
