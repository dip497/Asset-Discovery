package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.LogicalDiskRest;

import java.util.List;

public interface LogicalDiskService {
    void save(Long id);

    void deleteById(Long id, Long aLong);

    void update(Long refId, Long id, LogicalDiskRest logicalDiskRest);

    List<LogicalDiskRest> getAllLogicalDisks(Long refId);
}
