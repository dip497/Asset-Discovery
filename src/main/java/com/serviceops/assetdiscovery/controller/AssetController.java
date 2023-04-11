package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.AssetRest;

import com.serviceops.assetdiscovery.service.impl.AssetServiceImpl;
import com.serviceops.assetdiscovery.utils.AllAssetResponse;
import com.serviceops.assetdiscovery.utils.AppConstants;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AssetController {

    private final AssetServiceImpl assetService;
    Logger logger = LoggerFactory.getLogger(AssetController.class);

    public AssetController(AssetServiceImpl assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/asset")
    public AllAssetResponse getAllAssets(     @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
                                                              @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                              @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                                              @RequestParam(value = "sortDir", defaultValue=AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){

        logger.debug("Fetching Assets");

        return assetService.findPaginatedData(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/asset/{id}")
    public List<AssetRest> getAssets(@PathVariable("id") long id){

        List<AssetRest> assetRests = new ArrayList<>();

        assetRests.add(assetService.findById(id));

        logger.debug("Fetching Asset with id -> {}",id);

        return assetRests;
    }

    @PatchMapping("/asset/{id}")
    public void updateAssetField(@PathVariable Long id, @RequestBody Map<String, Object> fields){

        logger.debug("Updating Asset field -> {} for Asset id {}",fields,id);

        assetService.update(id,fields);

    }

    @DeleteMapping("/asset/{id}")
    public void deleteAssetById(@PathVariable Long id){

        logger.debug("Deleting Asset with id -> {}",id);

        assetService.deleteById(id);

    }

}
