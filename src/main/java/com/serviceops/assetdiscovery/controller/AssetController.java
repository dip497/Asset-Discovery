package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.AllAssetRest;
import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.service.impl.AssetServiceImpl;
import com.serviceops.assetdiscovery.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AssetController {
    private static final Logger logger = LoggerFactory.getLogger(AssetController.class);
    private final AssetServiceImpl assetService;

    public AssetController(AssetServiceImpl assetService) {
        this.assetService = assetService;
    }

    @GetMapping(value = "/asset", produces = MediaType.APPLICATION_JSON_VALUE)
    public AllAssetRest getAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        logger.debug("Fetching Assets");
        return assetService.findPaginatedAssetData(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping(value = "/asset/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssetRest getById(@PathVariable("id") long id) {
        logger.debug("Fetching Asset with id -> {}", id);
        return assetService.findById(id);
    }

    @DeleteMapping(value = "/asset/{id}")
    public boolean delete(@PathVariable long id) {
        logger.debug("Deleting Asset with id -> {}", id);
        return assetService.deleteById(id);
    }

    @PatchMapping(value = "/asset/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AssetRest update(@PathVariable long id, @RequestBody Map<String, Object> fields) {
        logger.debug("Updating Asset field -> {} for Asset id {}", fields, id);
        return assetService.updateById(id, fields);
    }
}
