package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.controller.PhysicalDiskController;
import com.serviceops.assetdiscovery.entity.MotherBoard;
import com.serviceops.assetdiscovery.entity.PhysicalDisk;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.service.interfaces.PhysicalDiskService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
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
    }

    public static void setCommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        // command for parsing information about the disk description.
        commands.put("udo lshw -class disk | grep -i description", new String[]{});
        // command for parsing information about the disk size.
        commands.put("sudo lshw -class disk | grep -i size:", new String[]{});
        // command for parsing information about the disk name.
        commands.put("sudo lshw -class disk | grep -i product:", new String[]{});


        LinuxCommandExecutorManager.add(MotherBoard.class, commands);
    }

    public static List<String> getParseResult() {
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(MotherBoard.class);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String[]> result : stringMap.entrySet()) {
            String[] values = result.getValue();
            for (int i = 2; i < values.length; i += 2) {
                list.add(values[i]);

            }

        }
        return list;
    }

    @Override
    public void save(Long id) {
        PhysicalDisk physicalDisk = new PhysicalDisk();
        physicalDisk.setRefId(id);
        physicalDisk.setDescription(getParseResult().get(0));
        // todo: find a better way to parse the size of the disk.  // size showing is "L1HF97B03HS"
        System.out.println(getParseResult().get(1));
        physicalDisk.setName(getParseResult().get(2));

        logger.info("Saving :" + physicalDisk);
        customRepository.save(physicalDisk);
    }

    @Override
    public void delete(Long id) {
        customRepository.deleteById(PhysicalDisk.class,id,"refId");
    }

    @Override
    public void findByRefId(Long id) {
        customRepository.findByColumn("refId",id,PhysicalDisk.class);
    }
}
