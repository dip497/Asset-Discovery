package com.serviceops.assetdiscovery.service.impl;

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

import java.util.List;
import java.util.Optional;

@Service
public class ComputerPropertiesServiceImpl implements ComputerPropertiesService {

    CustomRepository customRepository;
    private final RamServiceImpl ramService;
    Logger logger = LoggerFactory.getLogger(ComputerPropertiesServiceImpl.class);

    public ComputerPropertiesServiceImpl(CustomRepository customRepository, RamServiceImpl ramService) {
        this.customRepository = customRepository;
        this.ramService = ramService;
    }

    @Override
    public ComputerPropertiesRest findByRefId(Long refId) {

        Optional<OS> optionalOS = customRepository.findByColumn("refId", refId, OS.class);
        Optional<PhysicalDisk> optionalPhysicalDisk = customRepository.findByColumn("refId", refId, PhysicalDisk.class);
        Optional<Processor> optionalProcessor = customRepository.findByColumn("refId", refId, Processor.class);
        List<RamRest> ramRests = ramService.findAllByRefId(refId);
        ComputerPropertiesRest computerPropertiesRest = new ComputerPropertiesRest();

        if (optionalOS.isPresent()) {
            OS os = optionalOS.get();
            computerPropertiesRest.setOsName(os.getOsName());
            computerPropertiesRest.setOsVersion(os.getOsVersion());
            computerPropertiesRest.setActivationStatus(os.getActivationStatus());
            computerPropertiesRest.setOsManufacturer(os.getManufacturer());
            computerPropertiesRest.setOsArchitecture(os.getOsArchitecture());
        }

        if (optionalPhysicalDisk.isPresent()) {
            PhysicalDisk physicalDisk = optionalPhysicalDisk.get();
            computerPropertiesRest.setDiskSize(physicalDisk.getSize());
        }

        if (optionalProcessor.isPresent()) {
            Processor processor = optionalProcessor.get();
            computerPropertiesRest.setCpuSpeed(processor.getCpuSpeed());
            computerPropertiesRest.setNumberOfLogicalProcessors(processor.getCoreCount());
        }

        if (!(ramRests.isEmpty())) {
            long size = 0L;
            for (RamRest ram : ramRests) {
              //  size += Long.parseLong(ram.getSize());
            }
            computerPropertiesRest.setMemorySize(Long.toString(size));

        }

        logger.info("Computer Properties fetched for Asset Id -> {}", refId);
        return computerPropertiesRest;

    }

}
