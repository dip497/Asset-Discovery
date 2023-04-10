package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Asset;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.service.interfaces.AssetService;
import com.serviceops.assetdiscovery.utils.mapper.AssetOps;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AssetServiceImpl implements AssetService {

    AssetOps assetOps;
    CustomRepository customRepository;

    public AssetServiceImpl(CustomRepository customRepository){
        this.customRepository = customRepository;
        AssetOps.setCommands();
    }


    @Override
    public Long save() {
        Asset asset = new Asset();
        List<String> parseResult = AssetOps.getParseResult();
        AssetRest assetRest = new AssetRest();
        asset.setHostName(parseResult.get(0));
        asset.setDomainName(parseResult.get(1));
        asset.setIpAddress(parseResult.get(2));
        asset.setAssetType(parseResult.get(3));
        asset.setSerialNumber(parseResult.get(4));
        asset.setMacAddress(parseResult.get(5));
        asset.setSubNetMask(parseResult.get(6));


        customRepository.save(asset);

        return findByIpAddress(assetRest.getIpAddress()).getId();
    }

    @Override
    public AssetRest findByIpAddress(String ipAddress) {

        Asset asset = customRepository.findByColumn("ipAddress",ipAddress,Asset.class).get();
        AssetRest assetRest = new AssetRest();
        assetOps = new AssetOps(asset,assetRest);

        return assetOps.entityToRest(asset);
    }

    @Override
    public AssetRest findById(Long id) {

        Asset asset = customRepository.findByColumn("ipAddress",id.toString(),Asset.class).get();
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
