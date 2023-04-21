package com.serviceops.assetdiscovery.utils.mapper.base;

import com.serviceops.assetdiscovery.entity.base.SingleBase;
import com.serviceops.assetdiscovery.rest.base.SingleBaseRest;

public class SingleBaseOps<T extends SingleBase, S extends SingleBaseRest> extends BaseOps<T, S> {

    protected SingleBaseOps() {
    }

    @Override
    public S entityToRest(T entity, S rest) {
        super.entityToRest(entity, rest);
        rest.setCreatedById(entity.getCreatedById());
        rest.setCreatedTime(entity.getCreatedTime());
        rest.setUpdatedTime(entity.getUpdatedTime());
        rest.setUpdateById(entity.getUpdatedById());
        return rest;
    }

    @Override
    public T restToEntity(T entity, S rest) {
        super.restToEntity(entity, rest);
        entity.setCreatedById(rest.getCreatedById());
        entity.setCreatedTime(rest.getCreatedTime());
        entity.setUpdatedById(rest.getUpdateById());
        entity.setUpdatedTime(rest.getUpdatedTime());
        return entity;
    }
}
