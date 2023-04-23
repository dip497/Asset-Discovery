package com.serviceops.assetdiscovery.controller;


import com.serviceops.assetdiscovery.rest.KeyboardRest;
import com.serviceops.assetdiscovery.service.interfaces.KeyboardService;
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
public class KeyBoardController {
    private static final Logger logger = LoggerFactory.getLogger(KeyBoardController.class);
    private final KeyboardService keyboardService;

    /**
     * Constructor for KeyboardController that injects the KeyboardService.
     *
     * @param keyboardService the KeyboardService to be injected.
     */
    public KeyBoardController(KeyboardService keyboardService) {
        this.keyboardService = keyboardService;
    }

    /**
     * GET endpoint for retrieving a list of Keyboard objects by a given asset ID.
     *
     * @param refId the asset ID.
     * @return a list of KeyboardRest objects.
     */
    @GetMapping(value = "/{refId}/keyboard", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<KeyboardRest> getKeyboard(@PathVariable("refId") long refId) {
        logger.debug("Fetching Keyboard with Asset id -> {}", refId);
        return keyboardService.findAllByRefId(refId);
    }

    /**
     * PUT endpoint for updating a Keyboard object by a given asset ID and Keyboard ID.
     *
     * @param refId        the asset ID.
     * @param id           the Keyboard ID.
     * @param keyboardRest the updated KeyboardRest object.
     * @return the updated KeyboardRest object.
     */
    @PutMapping(value = "/{refId}/keyboard/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public KeyboardRest updateKeyboard(@PathVariable("refId") long refId, @PathVariable("id") long id,
            @RequestBody KeyboardRest keyboardRest) {
        logger.debug("Updating Keyboard with id -> {}", id);
        return keyboardService.updateById(refId, id, keyboardRest);
    }

    /**
     * DELETE endpoint for deleting a Keyboard object by a given asset ID and Keyboard ID.
     *
     * @param refId the asset ID.
     * @param id    the Keyboard ID.
     * @return a boolean indicating whether or not the deletion was successful.
     */
    @DeleteMapping("/{refId}/keyboard/{id}")
    public boolean deleteKeyboard(@PathVariable("refId") long refId, @PathVariable("id") long id) {
        logger.debug("Deleting Keyboard with id -> {}", id);
        return keyboardService.deleteById(refId, id);
    }
}
