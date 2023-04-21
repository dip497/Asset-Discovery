package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Users;
import com.serviceops.assetdiscovery.rest.UsersRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class UsersOps extends SingleBaseOps<Users, UsersRest> {
    @Override
    public UsersRest entityToRest(Users entity, UsersRest rest) {
        super.entityToRest(entity,rest);
        rest.setEmail(entity.getEmail());
        rest.setPassword(entity.getPassword());
        rest.setName(entity.getName());
        rest.setRole(entity.getRole());
        return rest;
    }
    @Override
    public  Users restToEntity(Users entity, UsersRest rest) {
        super.restToEntity(entity,rest);
        entity.setEmail(rest.getEmail());
        entity.setName(rest.getName());
        entity.setPassword(rest.getPassword());
        entity.setRole(rest.getRole());
        return entity;
    }
}
