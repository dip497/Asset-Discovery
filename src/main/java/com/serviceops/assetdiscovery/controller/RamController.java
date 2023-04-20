package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.RamRest;
import com.serviceops.assetdiscovery.service.interfaces.RamService;
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
public class RamController {
    private final RamService ramService;
    private final Logger logger = LoggerFactory.getLogger(RamController.class);

    public RamController(RamService motherBoardService) {
        this.ramService = motherBoardService;
    }

    @GetMapping(value = "/{refId}/ram", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RamRest> getRam(@PathVariable("refId") Long refId) {
        logger.debug("Fetching Ram with Asset id -> {}", refId);
        return ramService.findAllByRefId(refId);
    }

    @DeleteMapping("/{refId}/ram/{id}")
    public void deleteRam(@PathVariable("refId") Long refId, @PathVariable("id") Long id) {
        logger.debug("Deleting Ram with  id -> {}", id);
        ramService.deleteById(refId, id);
    }

    @PutMapping(value = "/{refId}/ram/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateRam(@PathVariable("refId") Long refId, @PathVariable("id") Long id,
            @RequestBody RamRest ramRest) {
        logger.debug("Updating Ram with  id -> {}", id);
        ramService.update(refId, id, ramRest);
    }
}
