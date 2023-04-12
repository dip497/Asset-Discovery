package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;

public interface PhysicalDiskService {

    public void save(Long id);
    public void delete(Long id);
    public void update(Long id,PhysicalDiskRest physicalDiskRest);
    public void findByRefId(Long id);
}
