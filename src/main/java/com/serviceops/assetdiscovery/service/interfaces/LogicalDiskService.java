package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.LogicalDiskRest;

import java.util.List;

public interface LogicalDiskService {
    void save(long id);

    void deleteById(long id, long aLong);

    void update(long refId, long id, LogicalDiskRest logicalDiskRest);

    List<LogicalDiskRest> getAllLogicalDisks(long refId);
}
