package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.ProcessorRest;
import com.serviceops.assetdiscovery.service.interfaces.ProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProcessorController {
    private final ProcessorService processorService;
    private final Logger logger = LoggerFactory.getLogger(ProcessorController.class);

    public ProcessorController(ProcessorService processorService) {
        this.processorService = processorService;
    }

    @GetMapping(value = "/{refId}/processor",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProcessorRest> findProcessorById(@PathVariable("refId") Long refId){
        logger.debug("finding processor with id: -->{}", refId);
        return processorService.findByRefId(refId);
    }

    @DeleteMapping("/{refId}/processor")
    public void deleteProcessorById(@PathVariable("refId") Long refId){
        logger.debug("deleting processor with id: -->{}", refId);
        processorService.deleteById(refId);
    }

    @PutMapping(value = "/{refId}/processor",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateProcessor(@PathVariable("refId") Long refId,@RequestBody ProcessorRest processorRest){
        logger.debug("updating processor with id: -->{}", refId);
        processorService.update(refId, processorRest);
    }
}
