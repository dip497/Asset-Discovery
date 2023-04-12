package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Asset;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.HardwarePropertiesRest;
import com.serviceops.assetdiscovery.service.interfaces.HardwarePropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HardwarePropertiesServiceImpl implements HardwarePropertiesService {

    CustomRepository  customRepository;
    Logger logger = LoggerFactory.getLogger(HardwarePropertiesServiceImpl.class);

    public HardwarePropertiesServiceImpl(CustomRepository customRepository){
        this.customRepository = customRepository;
    }

    @Override
    public HardwarePropertiesRest findByRefId(Long refId) {
        Optional<Asset> optionalAsset = customRepository.findByColumn("id",refId, Asset.class);

        if(optionalAsset.isPresent()){
            Asset asset = optionalAsset.get();
            HardwarePropertiesRest hardwarePropertiesRest = new HardwarePropertiesRest();
            hardwarePropertiesRest.setSerialNumber(asset.getSerialNumber());
            logger.info("Hardware Properties fetched with Asset Id ->{}",refId);
            return  hardwarePropertiesRest;
        }
        else {
            logger.error("No asset found with id ->{}",refId);
            throw new ResourceNotFoundException("AssetRest","id",Long.toString(refId));
        }

    }

    @Override
    public void update(Long refId,HardwarePropertiesRest hardwarePropertiesRest) {

        Optional<Asset> optionalAsset = customRepository.findByColumn("id",refId, Asset.class);

        if(optionalAsset.isPresent()){
            Asset asset = optionalAsset.get();

            asset.setSerialNumber(hardwarePropertiesRest.getSerialNumber());

            customRepository.update(asset);

            logger.info("Hardware Properties updated with Asset Id ->{}",refId);

        }
        else {
            logger.error("No asset found with id ->{}",refId);
            throw new ResourceNotFoundException("AssetRest","id",Long.toString(refId));
        }

    }
}
