package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.OSRest;

import java.util.List;

public interface OsService {

    void save(Long refId);

    List<OSRest> findByRefId(Long refId);

    void deleteByRefId(Long refId);

    void update(Long refId,OSRest osRest);

}
