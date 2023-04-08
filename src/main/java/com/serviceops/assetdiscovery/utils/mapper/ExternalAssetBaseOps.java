package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.mapped.ExternalAssetBase;
import com.serviceops.assetdiscovery.rest.ExternalAssetBaseRest;

public class ExternalAssetBaseOps<T extends ExternalAssetBase,S extends ExternalAssetBaseRest> extends AssetBaseOps<T,S> {
    T externalAssetBase;
    S externalAssetBaseRest;

    public ExternalAssetBaseOps(T externalAssetBase, S externalAssetBaseRest) {
        super(externalAssetBase, externalAssetBaseRest);
        this.externalAssetBase = externalAssetBase;
        this.externalAssetBaseRest = externalAssetBaseRest;
    }

    @Override
    public T restToEntity(S externalAssetBaseRest){
        super.restToEntity(externalAssetBaseRest);
        externalAssetBase.setManufacturer(externalAssetBaseRest.getManufacturer());
        externalAssetBase.setDeviceStatus(externalAssetBaseRest.getDeviceStatus());
        return base;
    }

    @Override
    public S entityToRest(T externalAssetBase){
        super.entityToRest(externalAssetBase);
        externalAssetBaseRest.setManufacturer(externalAssetBase.getManufacturer());
        externalAssetBaseRest.setDeviceStatus(externalAssetBase.getDeviceStatus());
        return externalAssetBaseRest;
    }

    public T getExternalAssetBase() {
        return externalAssetBase;
    }

    public void setExternalAssetBase(T externalAssetBase) {
        this.externalAssetBase = externalAssetBase;
    }

    public S getExternalAssetBaseRest() {
        return externalAssetBaseRest;
    }

    public void setExternalAssetBaseRest(S externalAssetBaseRest) {
        this.externalAssetBaseRest = externalAssetBaseRest;
    }
}
