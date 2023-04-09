package com.serviceops.assetdiscovery.utils.mapper.base;

import com.serviceops.assetdiscovery.entity.base.AssetBase;
import com.serviceops.assetdiscovery.rest.base.AssetBaseRest;

public class AssetBaseOps<T extends AssetBase,S extends AssetBaseRest>  extends SingleBaseOps<T,S> {
    T assetBase;
    S assetBaseRest;

    public AssetBaseOps(T assetBase, S assetBaseRest) {
        super(assetBase,assetBaseRest);
        this.assetBase = assetBase;
        this.assetBaseRest = assetBaseRest;
    }

    @Override
    public  T restToEntity(S rest){
        super.restToEntity(rest);
        assetBase.setRefId(rest.getRefId());
        assetBase.setSerialNumber(rest.getSerialNumber());
        assetBase.setManufacturer(rest.getManufacturer());
        assetBase.setDeviceStatus(rest.getDeviceStatus());
        return assetBase;
    }

    @Override
    public  S entityToRest(T assetBase){
        super.entityToRest(assetBase);
        assetBaseRest.setRefId(assetBase.getRefId());
        assetBaseRest.setSerialNumber(assetBase.getSerialNumber());
        assetBaseRest.setManufacturer(assetBase.getManufacturer());
        assetBaseRest.setDeviceStatus(assetBase.getDeviceStatus());
        return assetBaseRest;
    }
}
