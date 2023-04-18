package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.LogicalDisk;
import com.serviceops.assetdiscovery.rest.LogicalDiskRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

public class LogicalDiskOps extends AssetBaseOps<LogicalDisk, LogicalDiskRest> {

    private LogicalDisk logicalDisk;
    private LogicalDiskRest logicalDiskRest;
    public LogicalDiskOps(LogicalDisk logicalDisk, LogicalDiskRest logicalDiskRest) {
        super(logicalDisk, logicalDiskRest);
        this.logicalDisk=logicalDisk;
        this.logicalDiskRest=logicalDiskRest;
    }

    public LogicalDiskRest entityToRest(){
        super.entityToRest(logicalDisk);
        logicalDiskRest.setDescription(logicalDisk.getDescription());
        logicalDiskRest.setFileSystemType(logicalDisk.getFileSystemType());
        logicalDiskRest.setSize(logicalDisk.getSize());
        logicalDiskRest.setDriveType(logicalDisk.getDriveType());
        logicalDiskRest.setSerialNumber(logicalDisk.getSerialNumber());
        logicalDiskRest.setName(logicalDisk.getName());
        logicalDiskRest.setFreeSpace(logicalDisk.getFreeSpace());
        logicalDiskRest.setId(logicalDisk.getId());

        return logicalDiskRest;
    }

    public LogicalDisk restToEntity(){
        super.restToEntity(logicalDiskRest);
        logicalDisk.setDescription(logicalDiskRest.getDescription());
        logicalDisk.setSize(logicalDiskRest.getSize());
        logicalDisk.setDriveType(logicalDiskRest.getDriveType());
        logicalDisk.setSerialNumber(logicalDiskRest.getSerialNumber());
        logicalDisk.setName(logicalDiskRest.getName());
        logicalDisk.setFreeSpace(logicalDiskRest.getFreeSpace());
        logicalDisk.setId(logicalDiskRest.getId());

        return logicalDisk;
    }

}
