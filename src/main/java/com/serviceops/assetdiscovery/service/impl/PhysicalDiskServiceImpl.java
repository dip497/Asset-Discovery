package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.controller.PhysicalDiskController;
import com.serviceops.assetdiscovery.entity.PhysicalDisk;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.PhysicalDiskRest;
import com.serviceops.assetdiscovery.service.interfaces.PhysicalDiskService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.PhysicalDiskOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PhysicalDiskServiceImpl implements PhysicalDiskService {

    private final CustomRepository customRepository;
    private final Logger logger = LoggerFactory.getLogger(PhysicalDiskController.class);

    public PhysicalDiskServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }

    public static void setCommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        // command for parsing information about the disk size.
        commands.put("df -h | awk '$NF==\"/\"{printf \"%s\\n\", $2}'\n", new String[]{});
        // command for parsing information about the disk name.
        commands.put("blkid | awk -F: '{print $1}' | awk -F/ '{print $NF}'\n", new String[]{});
        // command for parsing information about the pnp device id.
        commands.put("udevadm info --query=property --name=/dev/sda | grep ID_SERIAL\n",new String[]{});
        // command for parsing information about the interface.
        commands.put("udevadm info --query=property --name=/dev/sda | grep ID_BUS\n",new String[]{});
        // command for parsing information about the media type.
        commands.put("udevadm info --query=property --name=/dev/sda | grep DEVTYPE\n",new String[]{});

        LinuxCommandExecutorManager.add(PhysicalDisk.class, commands);
    }

    public static List<String> getParseResult() {
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(PhysicalDisk.class);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String[]> result : stringMap.entrySet()) {
            String[] values = result.getValue();
            for (int i = 0; i < values.length; i ++) {
                list.add(values[i]);

            }

        }
        return list;
    }

    @Override
    public void save(Long id) {
        PhysicalDisk physicalDisk = new PhysicalDisk();
        physicalDisk.setRefId(id);
        physicalDisk.setSize(getParseResult().get(0));
        physicalDisk.setName(getParseResult().get(1));
        physicalDisk.setPnpDeviceId(getParseResult().get(2));
        physicalDisk.setInterfaceType(getParseResult().get(3));
        physicalDisk.setMediaType(getParseResult().get(4));
        logger.info("Saving PhysicalDisk with Id : --> {}",physicalDisk.getId());
        customRepository.save(physicalDisk);
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleted PhysicalDisk with Id : --> {}",id);
        customRepository.deleteById(PhysicalDisk.class,id,"refId");
    }

    @Override
    public void update(Long id, PhysicalDiskRest physicalDiskRest) {
        PhysicalDiskOps physicalDiskOps = new PhysicalDiskOps(new PhysicalDisk(),physicalDiskRest);
        logger.info("Updating PhysicalDisk with Id : --> {}",id);
        customRepository.update(physicalDiskOps);
    }

    @Override
    public void findByRefId(Long id) {
        logger.info("Find PhysicalDisk with Id : --> {}",id);
        customRepository.findByColumn("refId",id,PhysicalDisk.class);
    }
}

