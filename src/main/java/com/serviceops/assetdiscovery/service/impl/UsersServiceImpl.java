package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.UsersRest;
import com.serviceops.assetdiscovery.service.interfaces.UsersService;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    private final CustomRepository userRepository;

    public UsersServiceImpl(CustomRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UsersRest save(UsersRest usersRest) {

      //  userRepository.save();
        return usersRest;
    }
}
