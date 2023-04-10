package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.MotherBoard;
import com.serviceops.assetdiscovery.rest.MotherBoardRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

public class MotherBoardOps extends AssetBaseOps<MotherBoard, MotherBoardRest> {
    private final MotherBoard motherBoard;
    private final MotherBoardRest motherBoardRest;
    public MotherBoardOps(MotherBoard motherBoard, MotherBoardRest motherBoardRest) {
        super(motherBoard, motherBoardRest);
        this.motherBoard = motherBoard;
        this.motherBoardRest = motherBoardRest;
    }


    public MotherBoard restToEntity() {
         super.restToEntity(motherBoardRest);
         motherBoard.setVersion(motherBoardRest.getVersion());
         motherBoard.setInstalledDate(motherBoardRest.getInstalledDate());
         motherBoard.setPartNumber(motherBoardRest.getPartNumber());
         motherBoard.setPrimaryBusType(motherBoardRest.getPrimaryBusType());
         motherBoard.setSecondaryBusType(motherBoardRest.getSecondaryBusType());
         return motherBoard;

    }

    public MotherBoardRest entityToRest() {
         super.entityToRest(motherBoard);
        motherBoardRest.setVersion(motherBoard.getVersion());
        motherBoardRest.setInstalledDate(motherBoard.getInstalledDate());
        motherBoardRest.setPartNumber(motherBoard.getPartNumber());
        motherBoardRest.setPrimaryBusType(motherBoard.getPrimaryBusType());
        motherBoardRest.setSecondaryBusType(motherBoard.getSecondaryBusType());
        return motherBoardRest;
    }

}
