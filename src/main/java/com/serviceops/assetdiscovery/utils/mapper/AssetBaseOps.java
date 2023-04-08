package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.mapped.AssetBase;
import com.serviceops.assetdiscovery.rest.AssetBaseRest;

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
        return assetBase;
    }

    @Override
    public  S entityToRest(T assetBase){
        super.entityToRest(assetBase);
        assetBaseRest.setRefId(assetBase.getRefId());
        assetBaseRest.setSerialNumber(assetBase.getSerialNumber());
        return assetBaseRest;
    }

    public T getAssetBase() {
        return assetBase;
    }

    public void setAssetBase(T assetBase) {
        this.assetBase = assetBase;
    }

    public S getAssetBaseRest() {
        return assetBaseRest;
    }

    public void setAssetBaseRest(S assetBaseRest) {
        this.assetBaseRest = assetBaseRest;
    }
}
