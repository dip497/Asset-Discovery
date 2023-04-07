package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.service.interfaces.UsersService;

public class UsersServiceImpl implements UsersService {
    private final CustomRepository userRepository;

    public UsersServiceImpl(CustomRepository userRepository) {
        this.userRepository = userRepository;
    }
}
