package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.MotherBoardRest;
import com.serviceops.assetdiscovery.service.interfaces.MotherBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MotherBoardController {
    private static final Logger logger = LoggerFactory.getLogger(MotherBoardController.class);
    private final MotherBoardService motherBoardService;

    public MotherBoardController(MotherBoardService motherBoardService) {
        this.motherBoardService = motherBoardService;
    }

    @GetMapping(value = "/{refId}/motherBoard", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MotherBoardRest> getMotherBoard(@PathVariable("refId") long refId) {
        logger.debug("Fetching MotherBoard with Asset id -> {}", refId);
        return motherBoardService.findByRefId(refId);
    }

    @PutMapping(value = "/{refId}/motherBoard", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MotherBoardRest updateMotherBoard(@PathVariable("refId") long refId,
            @RequestBody MotherBoardRest motherBoardRest) {
        logger.debug("Updating MotherBoard with Asset id -> {}", refId);
        return motherBoardService.updateByRefId(refId, motherBoardRest);

    }

    @DeleteMapping("/{refId}/motherBoard")
    public boolean deleteMotherBoard(@PathVariable("refId") long refId) {
        logger.debug("Deleting MotherBoard with Asset id -> {}", refId);
        return motherBoardService.deleteByRefId(refId);

    }
}
