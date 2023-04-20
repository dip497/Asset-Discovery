package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Asset;
import com.serviceops.assetdiscovery.entity.ComputerSystem;
import com.serviceops.assetdiscovery.entity.OS;
import com.serviceops.assetdiscovery.entity.PhysicalDisk;
import com.serviceops.assetdiscovery.entity.Processor;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.ComputerPropertiesRest;
import com.serviceops.assetdiscovery.rest.RamRest;
import com.serviceops.assetdiscovery.service.interfaces.ComputerPropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComputerPropertiesServiceImpl implements ComputerPropertiesService {

    private final RamServiceImpl ramService;
    CustomRepository customRepository;
    private static final Logger logger = LoggerFactory.getLogger(ComputerPropertiesServiceImpl.class);

    public ComputerPropertiesServiceImpl(CustomRepository customRepository, RamServiceImpl ramService) {
        this.customRepository = customRepository;
        this.ramService = ramService;
    }

    @Override
    public List<ComputerPropertiesRest> findByRefId(long refId) {

        Optional<OS> optionalOS = customRepository.findByColumn("refId", refId, OS.class);
        Optional<PhysicalDisk> optionalPhysicalDisk =
                customRepository.findByColumn("refId", refId, PhysicalDisk.class);
        Optional<Processor> optionalProcessor =
                customRepository.findByColumn("refId", refId, Processor.class);
        Optional<Asset> optionalAsset = customRepository.findByColumn("id", refId, Asset.class);
        Optional<ComputerSystem> optionalComputerSystem =
                customRepository.findByColumn("refId", refId, ComputerSystem.class);
        List<RamRest> ramRests = ramService.findAllByRefId(refId);
        List<ComputerPropertiesRest> computerPropertiesRests = new ArrayList<>();
        ComputerPropertiesRest computerPropertiesRest = new ComputerPropertiesRest();

        if (optionalAsset.isPresent()) {
            Asset asset = optionalAsset.get();
            computerPropertiesRest.setLastLoggedInUser(asset.getLastLoggedUser());
        }

        if (optionalComputerSystem.isPresent()) {
            ComputerSystem computerSystem = optionalComputerSystem.get();
            computerPropertiesRest.setBootUpState(computerSystem.getBootUpState());
        }

        if (optionalOS.isPresent()) {
            OS os = optionalOS.get();
            computerPropertiesRest.setOsName(os.getOsName());
            computerPropertiesRest.setOsVersion(os.getOsVersion());
            computerPropertiesRest.setOsManufacturer(os.getManufacturer());
            computerPropertiesRest.setArchitecture(os.getOsArchitecture());
        }

        if (optionalPhysicalDisk.isPresent()) {
            PhysicalDisk physicalDisk = optionalPhysicalDisk.get();
            computerPropertiesRest.setDiskSize(physicalDisk.getSize());
        }

        if (optionalProcessor.isPresent()) {
            Processor processor = optionalProcessor.get();
            computerPropertiesRest.setCpuSpeed(processor.getCpuSpeed());
            computerPropertiesRest.setNumberOfProcessors(processor.getCoreCount());
            computerPropertiesRest.setNumberOfLogicalProcessors(2 * processor.getCoreCount());
        }

        if (!(ramRests.isEmpty())) {
            long size = 0L;
            for (RamRest ram : ramRests) {
                size += ram.getSize();
            }
            computerPropertiesRest.setMemorySize(size);

        }

        logger.info("Computer Properties fetched for Asset Id -> {}", refId);

        computerPropertiesRests.add(computerPropertiesRest);

        return computerPropertiesRests;

    }

}
