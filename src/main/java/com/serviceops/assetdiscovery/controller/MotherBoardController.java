package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.entity.MotherBoard;
import com.serviceops.assetdiscovery.rest.MotherBoardRest;
import com.serviceops.assetdiscovery.service.interfaces.MotherBoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MotherBoardController {
    private final MotherBoardService motherBoardService;

    public MotherBoardController(MotherBoardService motherBoardService) {
        this.motherBoardService = motherBoardService;
    }
}
