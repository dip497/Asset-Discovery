package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.utils.AllAssetResponse;

import java.util.List;
import java.util.Map;

public interface AssetService {

     AssetRest save();

     AssetRest findByIpAddress(String ipAddress);

     AssetRest findById(Long id);

     AllAssetResponse findPaginatedData(int pageNo, int pageSize, String sortBy, String sortDir);

     int findTotalCount();

     void deleteById(Long id);

     void update(Long id, Map<String, Object> fields);

}
