package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;

import java.util.List;

public interface PhysicalDiskService {

    void save(long refId);

    List<PhysicalDiskRest> findByRefId(long refId);

    PhysicalDiskRest updateByRefId(long refId, PhysicalDiskRest physicalDiskRest);

    boolean deleteByRefId(long refId);
}
