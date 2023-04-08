package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Users;
import com.serviceops.assetdiscovery.rest.UsersRest;

public class UsersOps extends SingleBaseOps<Users,UsersRest>{
    private Users users;
    private UsersRest usersRest;

    public UsersOps(Users users, UsersRest usersRest) {
        super(users, usersRest);
        this.users = users;
        this.usersRest = usersRest;
    }
    @Override
    public UsersRest entityToRest(Users users) {
        super.entityToRest(users);
        usersRest.setEmail(users.getEmail());
        usersRest.setPassword(users.getPassword());
        usersRest.setName(users.getName());
        return usersRest;
    }

    @Override
    public Users restToEntity(UsersRest usersRest) {
        super.restToEntity(usersRest);
        users.setEmail(usersRest.getEmail());
        users.setName(usersRest.getName());
        users.setPassword(usersRest.getPassword());
        return users;
    }
}
