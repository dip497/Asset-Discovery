package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.MotherBoard;
import com.serviceops.assetdiscovery.rest.MotherBoardRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class MotherBoardOps extends SingleBaseOps<MotherBoard, MotherBoardRest> {

    @Override
    public MotherBoard restToEntity(MotherBoard motherBoard, MotherBoardRest motherBoardRest) {
        super.restToEntity(motherBoard, motherBoardRest);
        motherBoard.setRefId(motherBoardRest.getRefId());
        motherBoard.setManufacturer(motherBoardRest.getManufacturer());
        motherBoard.setSerialNumber(motherBoardRest.getSerialNumber());
        motherBoard.setVersion(motherBoardRest.getVersion());
        return motherBoard;

    }

    @Override
    public MotherBoardRest entityToRest(MotherBoard motherBoard, MotherBoardRest motherBoardRest) {
        super.entityToRest(motherBoard, motherBoardRest);
        motherBoardRest.setRefId(motherBoard.getRefId());
        motherBoardRest.setManufacturer(motherBoard.getManufacturer());
        motherBoardRest.setSerialNumber(motherBoard.getSerialNumber());
        motherBoardRest.setVersion(motherBoard.getVersion());
        return motherBoardRest;
    }

}
