package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Asset;
import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class AssetOps extends SingleBaseOps<Asset,AssetRest> {

    private final Asset asset;
    private final AssetRest assetRest;

    public AssetOps(Asset asset, AssetRest assetRest) {
        super(asset, assetRest);
        this.asset = asset;
        this.assetRest = assetRest;
    }
    public Asset restToEntity(){
        super.restToEntity(assetRest);
        asset.setHostName(assetRest.getHostName());
        asset.setDomainName(assetRest.getDomainName());
        asset.setIpAddress(assetRest.getIpAddress());
        asset.setAssetType(assetRest.getAssetType());
        asset.setSerialNumber(assetRest.getSerialNumber());
        asset.setMacAddress(assetRest.getMacAddress());
        asset.setSubNetMask(assetRest.getSubNetMask());
        return asset;
    }

    public AssetRest entityToRest(){
        super.entityToRest(asset);
        assetRest.setHostName(asset.getHostName());
        assetRest.setDomainName(asset.getDomainName());
        assetRest.setIpAddress(asset.getIpAddress());
        assetRest.setAssetType(asset.getAssetType());
        assetRest.setSerialNumber(asset.getSerialNumber());
        assetRest.setMacAddress(asset.getMacAddress());
        assetRest.setSubNetMask(assetRest.getSubNetMask());
        return assetRest;
    }

}
