package com.serviceops.assetdiscovery.controller;


import com.serviceops.assetdiscovery.rest.KeyboardRest;
import com.serviceops.assetdiscovery.service.interfaces.KeyboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{refId}/keyBoard")
public class KeyBoardController {
    private final KeyboardService keyboardService;
    private final Logger logger = LoggerFactory.getLogger(KeyBoardController.class);

    public KeyBoardController(KeyboardService keyboardService) {
        this.keyboardService = keyboardService;
    }

    @GetMapping()
    public List<KeyboardRest> getKeyboard(@PathVariable("refId") long refId){

        List<KeyboardRest> keyboardRests = new ArrayList<>();

        keyboardRests.add(keyboardService.findByRefId(refId));

        logger.debug("Fetching KeyBoard with Asset id -> {}",refId);

        return keyboardRests;
    }

    @DeleteMapping()
    public void deleteKeyBoard(@PathVariable("refId") long refId){

        logger.debug("Deleting Keyboard with Asset id -> {}",refId);

        keyboardService.deleteByRefId(refId);

    }

    @PutMapping()
    public void updateKeyboard(@PathVariable("refId") long refId,@RequestBody KeyboardRest keyboardRest){

        logger.debug("Updating Keyboard with Asset id -> {}",refId);

        keyboardService.update(keyboardRest);

    }
}
