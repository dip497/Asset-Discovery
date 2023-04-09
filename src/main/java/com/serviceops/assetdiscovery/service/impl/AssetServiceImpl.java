package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Asset;
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
    public Long save() {
        List<String> parseResult = AssetOps.getParseResult();

        AssetRest assetRest = new AssetRest();
        assetRest.setHostName(parseResult.get(0));
        assetRest.setDomainName(parseResult.get(1));
        assetRest.setIpAddress(parseResult.get(2));
        assetRest.setAssetType(parseResult.get(3));
        assetRest.setSerialNumber(parseResult.get(4));
        assetRest.setMacAddress(parseResult.get(5));
        assetRest.setSubNetMask(parseResult.get(6));
        Asset asset = new Asset();
        assetOps = new AssetOps(asset,assetRest);

        asset = assetOps.restToEntity(assetRest);

        customRepository.save(asset);

        return findByIpAddress(assetRest.getIpAddress()).getId();
    }

    @Override
    public AssetRest findByIpAddress(String ipAddress) {

        Asset asset = customRepository.findByColumn("ip_address",ipAddress,Asset.class);
        AssetRest assetRest = new AssetRest();
        assetOps = new AssetOps(asset,assetRest);

        return assetOps.entityToRest(asset);
    }

    @Override
    public AssetRest findById(Long id) {

        Asset asset = customRepository.findByColumn("ip_address",id,Asset.class);
        AssetRest assetRest = new AssetRest();
        assetOps = new AssetOps(asset,assetRest);

        return assetOps.entityToRest(asset);
    }

    @Override
    public List<AssetRest> findAll() {

        assetOps = new AssetOps(new Asset(),new AssetRest()); // not valid
        List<Asset> assets = customRepository.findAll(Asset.class);

        return assets.stream().map((A)->assetOps.entityToRest(A)).toList();

    }


}
