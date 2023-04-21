package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.LogicalDisk;
import com.serviceops.assetdiscovery.rest.LogicalDiskRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AuditBaseOps;

public class LogicalDiskOps extends AuditBaseOps<LogicalDisk, LogicalDiskRest> {

    @Override
    public LogicalDiskRest entityToRest(LogicalDisk logicalDisk, LogicalDiskRest logicalDiskRest) {
        super.entityToRest(logicalDisk, logicalDiskRest);
        logicalDiskRest.setSerialNumber(logicalDisk.getSerialNumber());
        logicalDiskRest.setRefId(logicalDisk.getRefId());
        logicalDiskRest.setDescription(logicalDisk.getDescription());
        logicalDiskRest.setFileSystemType(logicalDisk.getFileSystemType());
        logicalDiskRest.setSize(logicalDisk.getSize());
        logicalDiskRest.setName(logicalDisk.getName());
        logicalDiskRest.setId(logicalDisk.getId());

        return logicalDiskRest;
    }

    @Override
    public LogicalDisk restToEntity(LogicalDisk logicalDisk, LogicalDiskRest logicalDiskRest) {
        super.restToEntity(logicalDisk, logicalDiskRest);
        logicalDisk.setSerialNumber(logicalDiskRest.getSerialNumber());
        logicalDisk.setRefId(logicalDiskRest.getRefId());
        logicalDisk.setFileSystemType(logicalDiskRest.getFileSystemType());
        logicalDisk.setDescription(logicalDiskRest.getDescription());
        logicalDisk.setSize(logicalDiskRest.getSize());
        logicalDisk.setName(logicalDiskRest.getName());
        logicalDisk.setId(logicalDiskRest.getId());

        return logicalDisk;
    }

}
