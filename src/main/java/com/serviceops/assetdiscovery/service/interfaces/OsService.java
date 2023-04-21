package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.OSRest;

import java.util.List;

public interface OsService {
    void save(long refId);

    List<OSRest> findByRefId(long refId);

    OSRest updateByRefId(long refId, OSRest osRest);

    boolean deleteByRefId(long refId);

}
