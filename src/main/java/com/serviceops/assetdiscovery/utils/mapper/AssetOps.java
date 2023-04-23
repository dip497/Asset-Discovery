package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Asset;
import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AuditBaseOps;

public class AssetOps extends AuditBaseOps<Asset, AssetRest> {

    @Override
    public Asset restToEntity(Asset asset,AssetRest assetRest) {
        super.restToEntity(asset,assetRest);
        asset.setHostName(assetRest.getHostName());
        asset.setDomainName(assetRest.getDomainName());
        asset.setIpAddress(assetRest.getIpAddress());
        asset.setAssetType(assetRest.getAssetType());
        asset.setSerialNumber(assetRest.getSerialNumber());
        asset.setMacAddress(assetRest.getMacAddress());
        asset.setSubNetMask(assetRest.getSubNetMask());
        asset.setLastLoggedUser(assetRest.getLastLoggedUser());
        asset.setHostId(assetRest.getHostId());
        return asset;
    }

    @Override
    public AssetRest entityToRest(Asset asset,AssetRest assetRest) {
        super.entityToRest(asset,assetRest);
        assetRest.setHostName(asset.getHostName());
        assetRest.setDomainName(asset.getDomainName());
        assetRest.setIpAddress(asset.getIpAddress());
        assetRest.setAssetType(asset.getAssetType());
        assetRest.setSerialNumber(asset.getSerialNumber());
        assetRest.setMacAddress(asset.getMacAddress());
        assetRest.setSubNetMask(asset.getSubNetMask());
        assetRest.setLastLoggedUser(asset.getLastLoggedUser());
        assetRest.setHostId(asset.getHostId());
        return assetRest;
    }

}
