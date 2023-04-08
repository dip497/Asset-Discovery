package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.mapped.Base;
import com.serviceops.assetdiscovery.rest.BaseRest;

public class BaseOps<T extends Base,S extends BaseRest>{
    T base;
    S baseRest;

    public BaseOps(T base , S baseRest){
        this.base = base;
        this.baseRest = baseRest;
    }
    public S entityToRest(T base) {
        baseRest.setId(base.getId());
        return baseRest;
    }
    public T restToEntity(S baseRest){
        base.setId(baseRest.getId());
        return base;
    }

    public T getBase() {
        return base;
    }

    public void setBase(T base) {
        this.base = base;
    }

    public S getBaseRest() {
        return baseRest;
    }

    public void setBaseRest(S baseRest) {
        this.baseRest = baseRest;
    }
}
