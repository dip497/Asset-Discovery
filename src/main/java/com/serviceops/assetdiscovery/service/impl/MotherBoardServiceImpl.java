package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.MotherBoard;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.MotherBoardRest;
import com.serviceops.assetdiscovery.service.interfaces.MotherBoardService;
import com.serviceops.assetdiscovery.utils.mapper.MotherBoardOps;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MotherBoardServiceImpl implements MotherBoardService{
    private MotherBoardOps  motherBoardOps;
    private CustomRepository customRepository;

    public MotherBoardServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        MotherBoardOps.setCommands();
    }
    @Override
    public void  save(MotherBoardRest motherBoardRest) {
        List<String> parseResult = MotherBoardOps.getParseResult();
        motherBoardRest.setManufacturer(parseResult.get(0));
        motherBoardRest.setSerialNumber(parseResult.get(1));
        motherBoardRest.setVersion(parseResult.get(2));
        MotherBoard motherBoard = new MotherBoard();
        motherBoardOps = new MotherBoardOps(motherBoard, motherBoardRest);
        motherBoard = motherBoardOps.restToEntity(motherBoardRest);
        customRepository.save(motherBoard);

    }

}
