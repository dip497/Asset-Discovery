package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.LogicalDiskRest;

import java.util.List;

public interface LogicalDiskService {
    void save(long id);

    boolean deleteById(long id, long aLong);

    void updateById(long refId, long id, LogicalDiskRest logicalDiskRest);

    List<LogicalDiskRest> findAllByRefId(long refId);
}
