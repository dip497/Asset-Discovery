package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.LogicalDiskRest;

import java.util.List;

public interface LogicalDiskService {
    void save(Long id);
    void deleteById(Long id);
    void update(LogicalDiskRest logicalDiskRest);
    List<LogicalDiskRest> getAllLogicalDisks(Long refId);
}
