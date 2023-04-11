package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.service.interfaces.ProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessorController {
    private final ProcessorService processorService;
    private final Logger logger = LoggerFactory.getLogger(ProcessorController.class);

    public ProcessorController(ProcessorService processorService) {
        this.processorService = processorService;
    }
}
