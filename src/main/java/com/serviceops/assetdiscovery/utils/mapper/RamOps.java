package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Ram;
import com.serviceops.assetdiscovery.rest.RamRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class RamOps extends SingleBaseOps<Ram, RamRest> {

    @Override
    public Ram restToEntity(Ram ram, RamRest ramRest) {
        super.restToEntity(ram,ramRest);
        ram.setRefId(ramRest.getRefId());
        ram.setManufacturer(ramRest.getManufacturer());
        ram.setSerialNumber(ramRest.getSerialNumber());
        ram.setSize(ramRest.getSize());
        ram.setMemoryType(ramRest.getMemoryType());
        ram.setWidth(ramRest.getWidth());
        ram.setClockSpeed(ramRest.getClockSpeed());
        ram.setBankLocater(ramRest.getBankLocater());
        return ram;

    }
    @Override
    public RamRest entityToRest(Ram ram, RamRest ramRest) {
        super.entityToRest(ram,ramRest);
        ramRest.setRefId(ram.getRefId());
        ramRest.setManufacturer(ram.getManufacturer());
        ramRest.setSerialNumber(ram.getSerialNumber());
        ramRest.setSize(ram.getSize());
        ramRest.setMemoryType(ram.getMemoryType());
        ramRest.setWidth(ram.getWidth());
        ramRest.setClockSpeed(ram.getClockSpeed());
        ramRest.setBankLocater(ram.getBankLocater());
        return ramRest;
    }
}
