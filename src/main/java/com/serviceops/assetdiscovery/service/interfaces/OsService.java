package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.OSRest;

public interface OsService {

    void save(Long refId);

    OSRest findByRefId(Long refId);

    void deleteByRefId(Long refId);

    void update(OSRest osRest);

}
