package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Asset;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.service.interfaces.AssetService;
import com.serviceops.assetdiscovery.utils.mapper.AssetOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.serviceops.assetdiscovery.utils.mapper.AssetOps.getParseResult;

@Service
public class AssetServiceImpl implements AssetService {

    CustomRepository customRepository;

    Logger logger = LoggerFactory.getLogger(AssetServiceImpl.class);

    public AssetServiceImpl(CustomRepository customRepository){
        this.customRepository = customRepository;
        AssetOps.setCommands();
    }


    @Override
    public Long save() {
        Asset asset = new Asset();
        List<String> parseResult = getParseResult();
        Optional<Asset> fetchAsset = customRepository.findByColumn("ipAddress", parseResult.get(2), Asset.class);
        if(fetchAsset.isPresent()) {
            Asset updatedAsset = fetchAsset.get();
            updatedAsset.setHostName(parseResult.get(0));
            updatedAsset.setDomainName(parseResult.get(1));
            updatedAsset.setMacAddress(parseResult.get(5));
            updatedAsset.setSubNetMask(parseResult.get(6));
            customRepository.save(updatedAsset);
            logger.debug("Updating asset with IP ->{}",parseResult.get(2));
            return findByIpAddress(updatedAsset.getIpAddress()).getId();
        }
        else {
            asset.setHostName(parseResult.get(0));
            asset.setDomainName(parseResult.get(1));
            asset.setIpAddress(parseResult.get(2));
            asset.setAssetType(parseResult.get(3));
            asset.setSerialNumber(parseResult.get(4));
            asset.setMacAddress(parseResult.get(5));
            asset.setSubNetMask(parseResult.get(6));
            customRepository.save(asset);
            logger.debug("Saving asset with IP ->{}",parseResult.get(2));
            return findByIpAddress(asset.getIpAddress()).getId();
        }



    }

    @Override
    public AssetRest findByIpAddress(String ipAddress) {

        Asset asset = customRepository.findByColumn("ipAddress",ipAddress,Asset.class).get();
        AssetRest assetRest = new AssetRest();
        AssetOps assetOps = new AssetOps(asset,assetRest);

        return assetOps.entityToRest(asset);
    }

    @Override
    public AssetRest findById(Long id) {

        Asset asset = customRepository.findByColumn("ipAddress",id.toString(),Asset.class).get();
        AssetRest assetRest = new AssetRest();
        AssetOps assetOps = new AssetOps(asset,assetRest);

        return assetOps.entityToRest(asset);
    }

    @Override
    public List<AssetRest> findAll() {

        AssetOps assetOps = new AssetOps(new Asset(),new AssetRest()); // not valid
        List<Asset> assets = customRepository.findAll(Asset.class);

        return assets.stream().map(assetOps::entityToRest).toList();

    }


}
