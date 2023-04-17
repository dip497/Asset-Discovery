package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.MotherBoardRest;
import com.serviceops.assetdiscovery.service.interfaces.MotherBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MotherBoardController {
    private final MotherBoardService motherBoardService;
    private final Logger logger = LoggerFactory.getLogger(MotherBoardController.class);

    public MotherBoardController(MotherBoardService motherBoardService) {
        this.motherBoardService = motherBoardService;
    }

    @GetMapping("/{refId}/motherBoard")
    public List<MotherBoardRest> getMotherBoard(@PathVariable("refId") Long refId){

        logger.debug("Fetching MotherBoard with Asset id -> {}",refId);

        return  motherBoardService.findByRefId(refId);
    }

    @DeleteMapping("/{refId}/motherBoard")
    public void deleteMotherBoard(@PathVariable("refId") Long refId){

        logger.debug("Deleting MotherBoard with Asset id -> {}",refId);

        motherBoardService.deleteByRefId(refId);

    }

    @PutMapping("/{refId}/motherBoard")
    public void updateMotherBoard(@PathVariable("refId") Long refId,@RequestBody MotherBoardRest motherBoardRest){

        logger.debug("Updating MotherBoard with Asset id -> {}",refId);

        motherBoardService.update(refId,motherBoardRest);

    }
}
