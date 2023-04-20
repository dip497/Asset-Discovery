package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.ComputerSystemRest;

import java.util.List;

public interface ComputerSystemService {

    void save(Long Id);

    List<ComputerSystemRest> get(Long id);

    void deleteById(Long id);

    void update(Long refId, ComputerSystemRest computerSystemRest);

}
