package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.ComputerSystem;
import com.serviceops.assetdiscovery.rest.ComputerSystemRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;


public class ComputerSystemOps extends AssetBaseOps<ComputerSystem, ComputerSystemRest> {

    private final ComputerSystem computerSystem;
    private final ComputerSystemRest computerSystemRest;

    public ComputerSystemOps(ComputerSystem computerSystem, ComputerSystemRest computerSystemRest) {
        super(computerSystem, computerSystemRest);
        this.computerSystem = computerSystem;
        this.computerSystemRest = computerSystemRest;
    }


    public ComputerSystem restToEntity() {
        super.restToEntity(computerSystemRest);
        computerSystem.setModelName(computerSystemRest.getModelName());
        computerSystem.setSystemType(computerSystemRest.getSystemType());
        computerSystem.setUuid(computerSystemRest.getUuid());
        computerSystem.setBootUpState(computerSystemRest.getBootUpState());
        computerSystem.setUserName(computerSystemRest.getUserName());

        return computerSystem;
    }

    public ComputerSystemRest entityToRest() {
        super.entityToRest(computerSystem);
        computerSystemRest.setModelName(computerSystem.getModelName());
        computerSystemRest.setSystemType(computerSystem.getSystemType());
        computerSystemRest.setUuid(computerSystem.getUuid());
        computerSystemRest.setBootUpState(computerSystem.getBootUpState());
        computerSystemRest.setUserName(computerSystem.getUserName());

        return computerSystemRest;
    }



}
