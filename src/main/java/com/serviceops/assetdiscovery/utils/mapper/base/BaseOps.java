package com.serviceops.assetdiscovery.utils.mapper.base;

import com.serviceops.assetdiscovery.entity.base.Base;
import com.serviceops.assetdiscovery.rest.base.BaseRest;

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
}
