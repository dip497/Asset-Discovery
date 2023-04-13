package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;

import java.util.List;

public interface PhysicalDiskService {

    public void save(Long id);
    public void delete(Long id);
    public void update(Long id,PhysicalDiskRest physicalDiskRest);
    public List<PhysicalDiskRest> findByRefId(Long id);
}
