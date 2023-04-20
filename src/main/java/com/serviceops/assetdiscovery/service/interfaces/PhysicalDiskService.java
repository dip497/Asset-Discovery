package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.entity.PhysicalDisk;
import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;

import java.util.List;

public interface PhysicalDiskService {

    PhysicalDisk save(long refId);

    void delete(long refId);

    PhysicalDiskRest update(long refId, PhysicalDiskRest physicalDiskRest);

    List<PhysicalDiskRest> findByRefId(long refId);
}
