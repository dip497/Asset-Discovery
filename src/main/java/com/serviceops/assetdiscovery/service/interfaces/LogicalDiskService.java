package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.LogicalDiskRest;

public interface LogicalDiskService {
    void save(Long id);
    void delete(Long id);
    void update(LogicalDiskRest logicalDiskRest);
}
