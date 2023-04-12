package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.ProcessorRest;
import com.serviceops.assetdiscovery.service.interfaces.ProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{refId}/processor")
public class ProcessorController {
    private final ProcessorService processorService;
    private final Logger logger = LoggerFactory.getLogger(ProcessorController.class);

    public ProcessorController(ProcessorService processorService) {
        this.processorService = processorService;
    }

    @GetMapping
    public void findProcessorById(@PathVariable("refId") String refId){
        logger.debug("finding processor with id: -->{}", refId);
        processorService.findByRefId(Long.parseLong(refId));
    }

    @DeleteMapping
    public void deleteProcessorById(@PathVariable("refId") String refId){
        logger.debug("deleting processor with id: -->{}", refId);
        processorService.deleteById(Long.parseLong(refId));
    }

    @PutMapping
    public void updateProcessor(@PathVariable("refId") String refId, ProcessorRest processorRest){
        logger.debug("updating processor with id: -->{}", refId);
        processorService.update(Long.parseLong(refId), processorRest);
    }
}
