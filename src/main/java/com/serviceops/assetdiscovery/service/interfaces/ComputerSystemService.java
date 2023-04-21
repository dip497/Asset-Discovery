package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.ComputerSystemRest;

import java.util.List;

public interface ComputerSystemService {

    void save(long Id);

    List<ComputerSystemRest> findByRefId(long id);

    ComputerSystemRest updateByRefId(long refId, ComputerSystemRest computerSystemRest);

    boolean deleteByRefId(long id);

}
