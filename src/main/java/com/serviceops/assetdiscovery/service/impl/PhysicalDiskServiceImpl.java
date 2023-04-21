package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.LogicalDisk;
import com.serviceops.assetdiscovery.entity.PhysicalDisk;
import com.serviceops.assetdiscovery.exception.ComponentNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;
import com.serviceops.assetdiscovery.service.interfaces.PhysicalDiskService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.PhysicalDiskOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PhysicalDiskServiceImpl implements PhysicalDiskService {

    private final CustomRepository customRepository;
    private static final Logger logger = LoggerFactory.getLogger(PhysicalDiskServiceImpl.class);

    public PhysicalDiskServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }

    @Override
    public void save(long refId) {

        Optional<PhysicalDisk> fetchPhysicalDisk = customRepository.findByColumn("refId", refId, PhysicalDisk.class);
        if (fetchPhysicalDisk.isPresent()) {
            PhysicalDisk physicalDisk = fetchPhysicalDisk.get();
            logger.info("Updating PhysicalDisk with Id : --> {}", physicalDisk.getId());
            setData(physicalDisk, refId);
        } else {
            PhysicalDisk physicalDisk = new PhysicalDisk();
            physicalDisk.setRefId(refId);
            logger.info("Saved PhysicalDisk with Id : --> {}", refId);
            setData(physicalDisk, refId);
        }
    }

    @Override
    public List<PhysicalDiskRest> findByRefId(long refId) {
        Optional<PhysicalDisk> optionalPhysicalDisk = customRepository.findByColumn("refId", refId, PhysicalDisk.class);
        List<PhysicalDiskRest> physicalDiskRests = new ArrayList<>();
        if (optionalPhysicalDisk.isPresent()) {
            PhysicalDiskRest physicalDiskRest = new PhysicalDiskRest();
            PhysicalDiskOps physicalDiskOps = new PhysicalDiskOps(optionalPhysicalDisk.get(), physicalDiskRest);
            physicalDiskRests.add(physicalDiskOps.entityToRest());
            logger.info("Fetched PhysicalDisk with Id : --> {}", refId);
        } else {
            logger.error("Physical disk not found with id; --> {}", refId);
            physicalDiskRests.add(new PhysicalDiskRest());
        }
        return physicalDiskRests;
    }

    @Override
    public PhysicalDiskRest updateByRefId(long refId, PhysicalDiskRest physicalDiskRest) {
        Optional<PhysicalDisk> optionalPhysicalDisk = customRepository.findByColumn("refId", refId, PhysicalDisk.class);
        if (optionalPhysicalDisk.isPresent()) {
            PhysicalDisk physicalDisk = optionalPhysicalDisk.get();
            PhysicalDiskOps physicalDiskOps = new PhysicalDiskOps(physicalDisk, physicalDiskRest);
            customRepository.save(physicalDiskOps.restToEntity());
            logger.info("Updating PhysicalDisk with Id : --> {}", refId);

            return physicalDiskOps.entityToRest();

        } else {
            logger.error("Physical disk not found with id; --> {}", refId);
            throw new ComponentNotFoundException("No PhysicalDisk", "refId", refId);
        }
    }

    @Override
    public boolean deleteByRefId(long refId) {
        logger.info("Deleted PhysicalDisk with Id : --> {}", refId);
        return customRepository.deleteById(PhysicalDisk.class, refId, "refId");
    }

    private void setCommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        // command for parsing information about the disk size.
        commands.put("df -h | awk '$NF==\"/\"{printf \"%s\\n\", $2}'\n", new String[]{});
        // command for parsing information about the disk name.
        commands.put("blkid | awk -F: '{print $1}' | awk -F/ '{print $NF}'", new String[]{});
        // command for parsing information about the serial number.
        commands.put("sudo lshw -c disk | grep serial: ", new String[]{});
        // command for parsing information about the interface.
        commands.put("udevadm info --query=property --name=/dev/sda | grep ID_BUS", new String[]{});
        // command for parsing information about the media type.
        commands.put("udevadm info --query=property --name=/dev/sda | grep DEVTYPE= ", new String[]{});
        // command for parsing information of disk manufacturer.
        commands.put("sudo lshw -c disk | grep vendor: | head -n 1", new String[]{});
        // command for parsing information of disk description.
        commands.put("sudo lshw -c disk | grep description: | head -n 1", new String[]{});
        // command for parsing information of disk model.
        commands.put("sudo lshw -c disk | grep version: | head -n 1", new String[]{});

        LinuxCommandExecutorManager.add(PhysicalDisk.class, commands);
    }

    private static List<String> getParseResult() {
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(PhysicalDisk.class);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String[]> result : stringMap.entrySet()) {
            String[] values = result.getValue();
            Collections.addAll(list, values);

        }
        return list;
    }

    private long convertToBaseUnit(String partialData) {
        long data;
        partialData = partialData.toLowerCase();
        if (partialData.contains("mib") || partialData.contains("mb")) {
            partialData = partialData.replaceAll("[^0-9]", "").trim();
            partialData = partialData.replaceAll(" + ", "");
            data = Long.parseLong(partialData) * 1024 * 1024;

        } else if (partialData.contains("g") || partialData.contains("gb")) {
            partialData = partialData.replaceAll("[^0-9]", "").trim();
            partialData = partialData.replaceAll(" + ", "");
            data = Long.parseLong(partialData) * 1024 * 1024 * 1024;
        } else {
            return Long.parseLong(partialData);
        }

        return data;
    }

    private String formatData(String data, String keyword) {

        if (data.contains(keyword)) {
            return (data.substring(data.indexOf(keyword) + keyword.length())).trim();
        } else {
            return null;
        }
    }

    private void setData(PhysicalDisk physicalDisk, long refId) {
        try {
            physicalDisk.setPartition(customRepository.findAllByColumn("refId", refId,LogicalDisk.class).size());
            physicalDisk.setSize(convertToBaseUnit(getParseResult().get(0)));
            physicalDisk.setName(getParseResult().get(1).equals("bash: blkid: command not found") ? null : getParseResult().get(1));
            physicalDisk.setSerialNumber(formatData(getParseResult().get(2), "serial: "));
            physicalDisk.setInterfaceType(formatData(getParseResult().get(3), "ID_BUS="));
            physicalDisk.setMediaType(formatData(getParseResult().get(4), "DEVTYPE="));
            physicalDisk.setManufacturer(formatData(getParseResult().get(5), "vendor:"));
            physicalDisk.setDescription(formatData(getParseResult().get(6), "description:"));
            physicalDisk.setModel(formatData(getParseResult().get(7), "version:"));
            customRepository.save(physicalDisk);
        } catch (IndexOutOfBoundsException e) {
            customRepository.save(physicalDisk);
            logger.info("index out of bound exception in physical disk with id: -->{} ", refId);
        }

    }
}

