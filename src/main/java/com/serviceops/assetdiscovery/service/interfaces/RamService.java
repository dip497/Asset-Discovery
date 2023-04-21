package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.RamRest;

import java.util.List;

public interface RamService {
    void save(long id);

    List<RamRest> findAllByRefId(long refId);

    RamRest updateById(long refId, long id, RamRest ramRest);

    boolean deleteById(long refId, long id);
}
