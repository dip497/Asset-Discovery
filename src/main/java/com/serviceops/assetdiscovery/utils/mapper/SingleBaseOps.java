package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.mapped.SingleBase;
import com.serviceops.assetdiscovery.rest.SingleBaseRest;

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


    public T getSingleBase() {
        return singleBase;
    }

    public void setSingleBase(T singleBase) {
        this.singleBase = singleBase;
    }

    public S getSingleBaseRest() {
        return singleBaseRest;
    }

    public void setSingleBaseRest(S singleBaseRest) {
        this.singleBaseRest = singleBaseRest;
    }
}
