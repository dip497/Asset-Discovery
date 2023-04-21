package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Bios;
import com.serviceops.assetdiscovery.rest.BiosRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class BiosOps extends SingleBaseOps<Bios, BiosRest> {

    private final Bios bios;
    private final BiosRest biosRest;

    public BiosOps(Bios bios, BiosRest biosRest) {
        super(bios, biosRest);
        this.bios = bios;
        this.biosRest = biosRest;
    }

    public Bios restToEntity() {
        super.restToEntity(biosRest);
        bios.setRefId(biosRest.getRefId());
        bios.setSmBiosVersion(biosRest.getSmBiosVersion());
        bios.setReleaseDate(biosRest.getReleaseDate());
        bios.setVersion(biosRest.getVersion());
        return bios;
    }

    public BiosRest entityToRest() {
        super.entityToRest(bios);
        biosRest.setRefId(bios.getRefId());
        biosRest.setSmBiosVersion(bios.getSmBiosVersion());
        biosRest.setReleaseDate(bios.getReleaseDate());
        biosRest.setVersion(bios.getVersion());
        return biosRest;
    }

}
