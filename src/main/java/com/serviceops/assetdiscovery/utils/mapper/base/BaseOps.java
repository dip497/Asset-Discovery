package com.serviceops.assetdiscovery.utils.mapper.base;

import com.serviceops.assetdiscovery.entity.base.Base;
import com.serviceops.assetdiscovery.rest.base.BaseRest;

public abstract class BaseOps<T extends Base, S extends BaseRest> {

    protected BaseOps() {

    }

    public S entityToRest(T entity, S rest) {
        rest.setId(entity.getId());
        return rest;
    }

    public T restToEntity(T entity, S rest) {
        entity.setId(rest.getId());
        return entity;
    }
}
