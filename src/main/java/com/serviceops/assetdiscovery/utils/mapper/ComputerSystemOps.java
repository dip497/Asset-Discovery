package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.ComputerSystem;
import com.serviceops.assetdiscovery.rest.ComputerSystemRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;


public class ComputerSystemOps extends SingleBaseOps<ComputerSystem, ComputerSystemRest> {

    @Override
    public ComputerSystem restToEntity(ComputerSystem computerSystem, ComputerSystemRest computerSystemRest) {
        super.restToEntity(computerSystem, computerSystemRest);
        computerSystem.setRefId(computerSystemRest.getRefId());
        computerSystem.setManufacturer(computerSystemRest.getManufacturer());
        computerSystem.setModelName(computerSystemRest.getModelName());
        computerSystem.setSystemType(computerSystemRest.getSystemType());
        computerSystem.setUuid(computerSystemRest.getUuid());
        computerSystem.setBootUpState(computerSystemRest.getBootUpState());
        computerSystem.setUserName(computerSystemRest.getUserName());

        return computerSystem;
    }

    @Override
    public ComputerSystemRest entityToRest(ComputerSystem computerSystem,
            ComputerSystemRest computerSystemRest) {
        super.entityToRest(computerSystem, computerSystemRest);
        computerSystemRest.setRefId(computerSystem.getRefId());
        computerSystemRest.setManufacturer(computerSystem.getManufacturer());
        computerSystemRest.setModelName(computerSystem.getModelName());
        computerSystemRest.setSystemType(computerSystem.getSystemType());
        computerSystemRest.setUuid(computerSystem.getUuid());
        computerSystemRest.setBootUpState(computerSystem.getBootUpState());
        computerSystemRest.setUserName(computerSystem.getUserName());

        return computerSystemRest;
    }



}
