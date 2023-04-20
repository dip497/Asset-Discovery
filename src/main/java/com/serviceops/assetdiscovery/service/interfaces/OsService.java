package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.OSRest;

import java.util.List;

public interface OsService {

    void save(long refId);

    List<OSRest> findByRefId(long refId);

    void deleteByRefId(long refId);

    void update(long refId, OSRest osRest);

}
