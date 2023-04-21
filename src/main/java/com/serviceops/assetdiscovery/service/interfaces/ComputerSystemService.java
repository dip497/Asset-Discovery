package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.ComputerSystemRest;

import java.util.List;

public interface ComputerSystemService {

    void save(long Id);

    List<ComputerSystemRest> findAllByRefId(long id);

    boolean deleteById(long id);

    void updateById(long refId, ComputerSystemRest computerSystemRest);

}
