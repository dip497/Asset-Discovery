package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;

import java.util.List;

public interface PhysicalDiskService {

    public void save(Long refId);
    public void delete(Long refId);
    public void update(Long refId,PhysicalDiskRest physicalDiskRest);
    public List<PhysicalDiskRest> findByRefId(Long refId);
}
