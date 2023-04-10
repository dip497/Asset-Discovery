package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.entity.ComputerSystem;
import com.serviceops.assetdiscovery.rest.ComputerSystemRest;

public interface ComputerSystemService {

    void save(Long Id);
    ComputerSystemRest getComputerSystem(Long id);
    void deleteComputerSystemById(Long id);
}
