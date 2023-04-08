package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.PointingDevice;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.UsersRest;
import com.serviceops.assetdiscovery.service.interfaces.UsersService;
import com.serviceops.assetdiscovery.utils.mapper.PointingDeviceOps;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    private final CustomRepository userRepository;
    private PointingDeviceOps pointingDeviceOps;

    public UsersServiceImpl(CustomRepository userRepository) {
        this.userRepository = userRepository;
       // this.pointingDeviceOps = new PointingDeviceOps();
    }

    @Override
    public UsersRest save(UsersRest usersRest) {

      //  userRepository.save();
        return usersRest;
    }
}
