package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;

import java.util.List;

public interface PhysicalDiskService {

    void save(Long refId);

    void delete(Long refId);

    void update(Long refId, PhysicalDiskRest physicalDiskRest);

    List<PhysicalDiskRest> findByRefId(Long refId);
}
