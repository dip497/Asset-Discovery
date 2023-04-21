package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.PhysicalDisk;
import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AuditBaseOps;

public class PhysicalDiskOps extends AuditBaseOps<PhysicalDisk, PhysicalDiskRest> {

    @Override
    public PhysicalDisk restToEntity(PhysicalDisk physicalDisk, PhysicalDiskRest physicalDiskRest) {
        super.restToEntity(physicalDisk, physicalDiskRest);
        physicalDisk.setRefId(physicalDiskRest.getRefId());
        physicalDisk.setManufacturer(physicalDiskRest.getManufacturer());
        physicalDisk.setDescription(physicalDiskRest.getDescription());
        physicalDisk.setName(physicalDiskRest.getName());
        physicalDisk.setSize(physicalDiskRest.getSize());
        physicalDisk.setPartition(physicalDiskRest.getPartition());
        physicalDisk.setMediaType(physicalDiskRest.getMediaType());
        physicalDisk.setInterfaceType(physicalDiskRest.getInterfaceType());
        physicalDisk.setSerialNumber(physicalDiskRest.getSerialNumber());

        return physicalDisk;
    }

    @Override
    public PhysicalDiskRest entityToRest(PhysicalDisk physicalDisk, PhysicalDiskRest physicalDiskRest) {
        super.entityToRest(physicalDisk, physicalDiskRest);
        physicalDiskRest.setSerialNumber(physicalDisk.getSerialNumber());
        physicalDiskRest.setManufacturer(physicalDisk.getManufacturer());
        physicalDiskRest.setRefId(physicalDisk.getRefId());
        physicalDiskRest.setDescription(physicalDisk.getDescription());
        physicalDiskRest.setName(physicalDisk.getName());
        physicalDiskRest.setSize(physicalDisk.getSize());
        physicalDiskRest.setPartition(physicalDisk.getPartition());
        physicalDiskRest.setMediaType(physicalDisk.getMediaType());
        physicalDiskRest.setInterfaceType(physicalDisk.getInterfaceType());

        return physicalDiskRest;
    }
}
