package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.PhysicalDisk;
import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

public class PhysicalDiskOps extends AssetBaseOps<PhysicalDisk, PhysicalDiskRest> {
    private PhysicalDisk physicalDisk;
    private PhysicalDiskRest physicalDiskRest;

    public PhysicalDiskOps(PhysicalDisk physicalDisk, PhysicalDiskRest physicalDiskRest) {
        super(physicalDisk, physicalDiskRest);
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
        physicalDiskRest.setPnpDeviceId(physicalDiskRest.getPnpDeviceId());
        physicalDiskRest.setDescription(physicalDiskRest.getDescription());
        physicalDiskRest.setName(physicalDiskRest.getName());
        physicalDiskRest.setSize(physicalDiskRest.getSize());
        physicalDiskRest.setInstalledDate(physicalDiskRest.getInstalledDate());
        physicalDiskRest.setPartition(physicalDiskRest.getPartition());
        physicalDiskRest.setMediaType(physicalDiskRest.getMediaType());
        physicalDiskRest.setInterfaceType(physicalDiskRest.getInterfaceType());
        physicalDiskRest.setPnpDeviceId(physicalDiskRest.getPnpDeviceId());

        return physicalDiskRest;
    }
}
