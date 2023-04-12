package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.MotherBoardRest;
import com.serviceops.assetdiscovery.service.interfaces.MotherBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{refId}/motherBoard")
public class MotherBoardController {
    private final MotherBoardService motherBoardService;
    private final Logger logger = LoggerFactory.getLogger(MotherBoardController.class);

    public MotherBoardController(MotherBoardService motherBoardService) {
        this.motherBoardService = motherBoardService;
    }

    @GetMapping()
    public List<MotherBoardRest> getMotherBoard(@PathVariable("refId") long refId){

        List<MotherBoardRest> motherBoardRests = new ArrayList<>();

        motherBoardRests.add(motherBoardService.findByRefId(refId));

        logger.debug("Fetching MotherBoard with Asset id -> {}",refId);

        return motherBoardRests;
    }

    @DeleteMapping()
    public void deleteMotherBoard(@PathVariable("refId") long refId){

        logger.debug("Deleting MotherBoard with Asset id -> {}",refId);

        motherBoardService.deleteByRefId(refId);

    }

    @PutMapping()
    public void updateMotherBoard(@PathVariable("refId") long refId,@RequestBody MotherBoardRest motherBoardRest){

        logger.debug("Updating MotherBoard with Asset id -> {}",refId);

        motherBoardService.update(motherBoardRest);

    }
}
