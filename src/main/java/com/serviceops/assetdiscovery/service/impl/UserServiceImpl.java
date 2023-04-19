package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Users;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.UsersRest;
import com.serviceops.assetdiscovery.service.interfaces.UsersService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UsersService {

    private final CustomRepository customRepository;

    public UserServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
    }


    @Override
    public UsersRest save(UsersRest usersRest) {
        return null;
    }

    @Override
    public Boolean checkForUserInDB(String email) {

        Optional<Users> users = customRepository.findByColumn("email",email, Users.class);

        return users.isPresent();

    }
}
