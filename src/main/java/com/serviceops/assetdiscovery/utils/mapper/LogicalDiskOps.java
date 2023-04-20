package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.LogicalDisk;
import com.serviceops.assetdiscovery.rest.LogicalDiskRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class LogicalDiskOps extends SingleBaseOps<LogicalDisk, LogicalDiskRest> {

    private final LogicalDisk logicalDisk;
    private final LogicalDiskRest logicalDiskRest;

    public LogicalDiskOps(LogicalDisk logicalDisk, LogicalDiskRest logicalDiskRest) {
        super(logicalDisk, logicalDiskRest);
        this.logicalDisk = logicalDisk;
        this.logicalDiskRest = logicalDiskRest;
    }

    public LogicalDiskRest entityToRest() {
        super.entityToRest(logicalDisk);
        logicalDiskRest.setSerialNumber(logicalDisk.getSerialNumber());
        logicalDiskRest.setRefId(logicalDisk.getRefId());
        logicalDiskRest.setDescription(logicalDisk.getDescription());
        logicalDiskRest.setFileSystemType(logicalDisk.getFileSystemType());
        logicalDiskRest.setSize(logicalDisk.getSize());
        logicalDiskRest.setName(logicalDisk.getName());
        logicalDiskRest.setId(logicalDisk.getId());

        return logicalDiskRest;
    }

    public LogicalDisk restToEntity() {
        super.restToEntity(logicalDiskRest);
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
