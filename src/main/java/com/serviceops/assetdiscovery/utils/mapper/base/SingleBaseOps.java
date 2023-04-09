package com.serviceops.assetdiscovery.utils.mapper.base;

import com.serviceops.assetdiscovery.entity.base.SingleBase;
import com.serviceops.assetdiscovery.rest.base.SingleBaseRest;

public class SingleBaseOps<T extends SingleBase,S extends SingleBaseRest> extends BaseOps<T,S>{
    T singleBase;
    S singleBaseRest;

    public SingleBaseOps(T singleBase, S singleBaseRest) {
        super(singleBase, singleBaseRest);
        this.singleBase = singleBase;
        this.singleBaseRest = singleBaseRest;
    }

    @Override
    public S entityToRest(T singleBase) {
        super.entityToRest(singleBase);
        singleBaseRest.setCreatedBy(singleBase.getCreatedBy());
        singleBaseRest.setCreatedTime(singleBase.getCreatedTime());
        singleBaseRest.setUpdatedTime(singleBase.getUpdatedTime());
        singleBaseRest.setUpdatedBy(singleBase.getUpdatedBy());
        return singleBaseRest;
    }

    @Override
    public T restToEntity(S singleBaseRest) {
        super.restToEntity(singleBaseRest);
        singleBase.setCreatedBy(singleBaseRest.getCreatedBy());
        singleBase.setCreatedTime(singleBaseRest.getCreatedTime());
        singleBase.setUpdatedBy(singleBaseRest.getUpdatedBy());
        singleBase.setUpdatedTime(singleBaseRest.getUpdatedTime());
        return singleBase;
    }
}
