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
    private static final Logger logger = LoggerFactory.getLogger(RamController.class);
    private final RamService ramService;

    /**
     * Constructor for RamController that injects the RamService.
     * @param ramService the RamService to be injected.
     */
    public RamController(RamService ramService) {
        this.ramService = ramService;
    }

    /**
     * GET endpoint for retrieving a list of RAM objects by a given asset ID.
     * @param refId the asset ID.
     * @return a list of RamRest objects.
     */
    @GetMapping(value = "/{refId}/ram", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RamRest> getRam(@PathVariable("refId") long refId) {
        logger.debug("Fetching Ram with Asset id -> {}", refId);
        return ramService.findAllByRefId(refId);
    }

    /**
     * PUT endpoint for updating a RAM object by a given asset ID and RAM ID.
     * @param refId the asset ID.
     * @param id the RAM ID.
     * @param ramRest the updated RamRest object.
     * @return the updated RamRest object.
     */
    @PutMapping(value = "/{refId}/ram/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RamRest updateRam(@PathVariable("refId") long refId, @PathVariable("id") long id,
            @RequestBody RamRest ramRest) {
        logger.debug("Updating Ram with  id -> {}", id);
        return ramService.updateById(refId, id, ramRest);
    }

    /**
     * DELETE endpoint for deleting a RAM object by a given asset ID and RAM ID.
     * @param refId the asset ID.
     * @param id the RAM ID.
     * @return a boolean indicating whether or not the deletion was successful.
     */
    @DeleteMapping("/{refId}/ram/{id}")
    public boolean deleteRam(@PathVariable("refId") long refId, @PathVariable("id") long id) {
        logger.debug("Deleting Ram with  id -> {}", id);
        return ramService.deleteById(refId, id);
    }
}
