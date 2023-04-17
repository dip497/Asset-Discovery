package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.ComputerSystemRest;

public interface ComputerSystemService {

    void save(Long Id);
    ComputerSystemRest get(Long id);
    void deleteById(Long id);
    void update(Long refId, ComputerSystemRest computerSystemRest);

}
