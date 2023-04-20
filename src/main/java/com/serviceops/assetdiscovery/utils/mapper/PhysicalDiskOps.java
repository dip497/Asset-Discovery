package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.PhysicalDisk;
import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class PhysicalDiskOps extends SingleBaseOps<PhysicalDisk, PhysicalDiskRest> {
    private final PhysicalDisk physicalDisk;
    private final PhysicalDiskRest physicalDiskRest;

    public PhysicalDiskOps(PhysicalDisk physicalDisk, PhysicalDiskRest physicalDiskRest) {
        super(physicalDisk, physicalDiskRest);
        this.physicalDisk = physicalDisk;
        this.physicalDiskRest = physicalDiskRest;
    }

    public PhysicalDisk restToEntity() {
        super.restToEntity(physicalDiskRest);
        physicalDisk.setRefId(physicalDiskRest.getRefId());
        physicalDisk.setManufacturer(physicalDiskRest.getManufacturer());
        physicalDisk.setDescription(physicalDiskRest.getDescription());
        physicalDisk.setName(physicalDiskRest.getName());
        physicalDisk.setSize(physicalDiskRest.getSize());
        physicalDisk.setPartition(physicalDiskRest.getPartition());
        physicalDisk.setMediaType(physicalDiskRest.getMediaType());
        physicalDisk.setInterfaceType(physicalDiskRest.getInterfaceType());

        return physicalDisk;
    }

    public PhysicalDiskRest entityToRest() {
        super.entityToRest(physicalDisk);
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
