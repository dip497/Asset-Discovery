package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Ram;
import com.serviceops.assetdiscovery.rest.RamRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

public class RamOps extends AssetBaseOps<Ram, RamRest> {
    private Ram ram;
    private RamRest ramRest;

    public RamOps(Ram ram, RamRest ramRest) {
        super(ram, ramRest);
        this.ram = ram;
        this.ramRest  = ramRest;
    }

    @Override
    public Ram restToEntity(RamRest ramRest) {
         super.restToEntity(ramRest);
         ram.setSize(ramRest.getSize());
         ram.setMemoryType(ramRest.getMemoryType());
         ram.setWidth(ramRest.getWidth());
         ram.setClockSpeed(ramRest.getClockSpeed());
         ram.setBankLocater(ramRest.getBankLocater());
         return ram;

    }

    @Override
    public RamRest entityToRest(Ram ram) {
        super.entityToRest(ram);
        ramRest.setSize(ram.getSize());
        ramRest.setMemoryType(ram.getMemoryType());
        ramRest.setWidth(ram.getWidth());
        ramRest.setClockSpeed(ram.getClockSpeed());
        ramRest.setBankLocater(ram.getBankLocater());
        return ramRest;

    }
}
