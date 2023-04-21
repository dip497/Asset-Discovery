package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Users;
import com.serviceops.assetdiscovery.rest.UsersRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class UsersOps extends SingleBaseOps<Users, UsersRest> {
    private final Users users;
    private final UsersRest usersRest;

    public UsersOps(Users users, UsersRest usersRest) {
        super(users, usersRest);
        this.users = users;
        this.usersRest = usersRest;
    }

    public UsersRest entityToRest() {
        super.entityToRest(users);
        usersRest.setEmail(users.getEmail());
        usersRest.setPassword(users.getPassword());
        usersRest.setName(users.getName());
        usersRest.setRole(users.getRole());
        return usersRest;
    }

    public Users restToEntity() {
        super.restToEntity(usersRest);
        users.setEmail(usersRest.getEmail());
        users.setName(usersRest.getName());
        users.setPassword(usersRest.getPassword());
        users.setRole(usersRest.getRole());
        return users;
    }
}
