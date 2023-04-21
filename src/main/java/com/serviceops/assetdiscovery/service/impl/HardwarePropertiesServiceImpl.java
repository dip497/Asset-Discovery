package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Asset;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.HardwarePropertiesRest;
import com.serviceops.assetdiscovery.service.interfaces.HardwarePropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HardwarePropertiesServiceImpl implements HardwarePropertiesService {

    private static final Logger logger = LoggerFactory.getLogger(HardwarePropertiesServiceImpl.class);
    private final CustomRepository customRepository;

    public HardwarePropertiesServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
    }

    // Finding the Hardware Properties for specific Asset
    @Override
    public List<HardwarePropertiesRest> findByRefId(long refId) {
        Optional<Asset> optionalAsset = customRepository.findByColumn("id", refId, Asset.class);
        HardwarePropertiesRest hardwarePropertiesRest = new HardwarePropertiesRest();

        // If Asset is present then set the Required values in Hardware Properties
        if (optionalAsset.isPresent()) {
            Asset asset = optionalAsset.get();

            // Adding the serial number
            hardwarePropertiesRest.setSerialNumber(asset.getSerialNumber());

            logger.info("Hardware Properties fetched with Asset Id ->{}", refId);
        } else {
            logger.error("No asset found with id ->{}", refId);
        }

        List<HardwarePropertiesRest> hardwarePropertiesRests = new ArrayList<>();
        hardwarePropertiesRests.add(hardwarePropertiesRest);
        return hardwarePropertiesRests;

    }

}
