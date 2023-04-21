package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Bios;
import com.serviceops.assetdiscovery.rest.BiosRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class BiosOps extends SingleBaseOps<Bios, BiosRest> {

    @Override
    public Bios restToEntity(Bios bios,BiosRest biosRest) {
        super.restToEntity(bios,biosRest);
        bios.setRefId(biosRest.getRefId());
        bios.setSmBiosVersion(biosRest.getSmBiosVersion());
        bios.setReleaseDate(biosRest.getReleaseDate());
        bios.setSerialNumber(biosRest.getSerialNumber());
        bios.setManufacturer(biosRest.getManufacturer());
        bios.setVersion(biosRest.getVersion());
        return bios;
    }

    @Override
    public BiosRest entityToRest(Bios bios,BiosRest biosRest) {
        super.entityToRest(bios,biosRest);
        biosRest.setRefId(bios.getRefId());
        biosRest.setSmBiosVersion(bios.getSmBiosVersion());
        biosRest.setReleaseDate(bios.getReleaseDate());
        biosRest.setSerialNumber(bios.getSerialNumber());
        biosRest.setManufacturer(bios.getManufacturer());
        biosRest.setVersion(bios.getVersion());
        return biosRest;
    }

}
