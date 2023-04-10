package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.AssetRest;

import java.util.List;

public interface AssetService {

    public Long save();

    public AssetRest findByIpAddress(String ipAddress);

    public AssetRest findById(Long id);

    public List<AssetRest> findAll();

}
