package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.ComputerSystem;
import com.serviceops.assetdiscovery.rest.ComputerSystemRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;


public class ComputerSystemOps extends SingleBaseOps<ComputerSystem, ComputerSystemRest> {

    private final ComputerSystem computerSystem;
    private final ComputerSystemRest computerSystemRest;

    public ComputerSystemOps(ComputerSystem computerSystem, ComputerSystemRest computerSystemRest) {
        super(computerSystem, computerSystemRest);
        this.computerSystem = computerSystem;
        this.computerSystemRest = computerSystemRest;
    }


    public ComputerSystem restToEntity() {
        super.restToEntity(computerSystemRest);
        computerSystem.setRefId(computerSystemRest.getRefId());
        computerSystem.setManufacturer(computerSystemRest.getManufacturer());
        computerSystem.setModelName(computerSystemRest.getModelName());
        computerSystem.setSystemType(computerSystemRest.getSystemType());
        computerSystem.setUuid(computerSystemRest.getUuid());
        computerSystem.setBootUpState(computerSystemRest.getBootUpState());
        computerSystem.setUserName(computerSystemRest.getUserName());

        return computerSystem;
    }

    public ComputerSystemRest entityToRest() {
        super.entityToRest(computerSystem);
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
