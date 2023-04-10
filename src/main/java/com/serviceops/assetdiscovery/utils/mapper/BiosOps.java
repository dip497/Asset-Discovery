package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Bios;
import com.serviceops.assetdiscovery.entity.MotherBoard;
import com.serviceops.assetdiscovery.rest.BiosRest;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiosOps extends AssetBaseOps<Bios, BiosRest> {

    private final Bios bios;
    private final BiosRest biosRest;

    public BiosOps(Bios bios, BiosRest biosRest) {
        super(bios, biosRest);
        this.bios = bios;
        this.biosRest = biosRest;
    }


    public Bios restToEntity() {
        super.restToEntity(biosRest);
        bios.setName(biosRest.getName());
        bios.setSmBiosVersion(biosRest.getSmBiosVersion());
        bios.setRealeaseDate(biosRest.getRealeaseDate());
        bios.setVersion(biosRest.getVersion());
        bios.setDescription(biosRest.getDescription());
        return bios;
    }


    public BiosRest entityToRest() {
        super.entityToRest(bios);
        biosRest.setName(bios.getName());
        biosRest.setSmBiosVersion(bios.getSmBiosVersion());
        biosRest.setRealeaseDate(bios.getRealeaseDate());
        biosRest.setVersion(bios.getVersion());
        biosRest.setDescription(bios.getDescription());
        return biosRest;
    }

}
