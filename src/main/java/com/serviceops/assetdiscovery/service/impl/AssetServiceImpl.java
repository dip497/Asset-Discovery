package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.service.interfaces.AssetService;
import com.serviceops.assetdiscovery.utils.mapper.AssetOps;

import java.util.List;

public class AssetServiceImpl implements AssetService {

    AssetOps assetOps;
    CustomRepository customRepository;

    public AssetServiceImpl(CustomRepository customRepository){
        this.customRepository = customRepository;
        AssetOps.setCommands();
    }


    @Override
    public void save(AssetRest assetRest) {
        List<String> parseResult = AssetOps.getParseResult();

    }
}
