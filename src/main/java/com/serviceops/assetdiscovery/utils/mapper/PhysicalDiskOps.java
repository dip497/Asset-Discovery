package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.PhysicalDisk;
import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

public class PhysicalDiskOps extends AssetBaseOps<PhysicalDisk, PhysicalDiskRest> {
    private final PhysicalDisk physicalDisk;
    private final PhysicalDiskRest physicalDiskRest;

    public PhysicalDiskOps(PhysicalDisk physicalDisk, PhysicalDiskRest physicalDiskRest) {
        super(physicalDisk, physicalDiskRest);
        this.physicalDisk = physicalDisk;
        this.physicalDiskRest = physicalDiskRest;
    }

    public PhysicalDisk restToEntity(){
        super.restToEntity(physicalDiskRest);
        physicalDisk.setRefId(physicalDiskRest.getRefId());
        physicalDisk.setDescription(physicalDiskRest.getDescription());
        physicalDisk.setName(physicalDiskRest.getName());
        physicalDisk.setSize(physicalDiskRest.getSize());
        physicalDisk.setInstalledDate(physicalDiskRest.getInstalledDate());
        physicalDisk.setPartition(physicalDiskRest.getPartition());
        physicalDisk.setMediaType(physicalDiskRest.getMediaType());
        physicalDisk.setPnpDeviceId(physicalDiskRest.getPnpDeviceId());
        physicalDisk.setInterfaceType(physicalDiskRest.getInterfaceType());

        return physicalDisk;
    }

    public PhysicalDiskRest entityToRest(){
        super.entityToRest(physicalDisk);
        physicalDiskRest.setDescription(physicalDisk.getDescription());
        physicalDiskRest.setName(physicalDisk.getName());
        physicalDiskRest.setSize(physicalDisk.getSize());
        physicalDiskRest.setInstalledDate(physicalDisk.getInstalledDate());
        physicalDiskRest.setPartition(physicalDisk.getPartition());
        physicalDiskRest.setMediaType(physicalDisk.getMediaType());
        physicalDiskRest.setInterfaceType(physicalDisk.getInterfaceType());
        physicalDiskRest.setPnpDeviceId(physicalDisk.getPnpDeviceId());

        return physicalDiskRest;
    }
}
