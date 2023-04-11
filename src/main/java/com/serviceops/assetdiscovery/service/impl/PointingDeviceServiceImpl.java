package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.controller.PointingDeviceController;
import com.serviceops.assetdiscovery.entity.PointingDevice;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.service.interfaces.PointingDeviceService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.PointingDeviceOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PointingDeviceServiceImpl implements PointingDeviceService {
    private final Logger logger = LoggerFactory.getLogger(PointingDeviceController.class);
//    private  PointingDeviceOps pointingDeviceOps;
    private final CustomRepository customRepository;

    public PointingDeviceServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }

    private static void setCommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        // Command for getting information about pointing device Type.
        commands.put("sudo dmidecode -t 21 | grep -i Type: ", new String[]{});
        // Command for getting information about pointing device button.
        commands.put("sudo dmidecode -t 21 | grep -i Buttons: | awk '{print $NF}'", new String[]{});
        // Command for getting information about pointing device interface.
        commands.put("sudo dmidecode -t 21 | grep -i Interface: | awk '{print $NF}'", new String[]{});
        LinuxCommandExecutorManager.add(PointingDevice.class, commands);
    }

    private static List<String> getParseResult() {
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(PointingDevice.class);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String[]> result : stringMap.entrySet()) {
            String[] values = result.getValue();
            for (String value : values) {
                if (value.isEmpty()) {
                    list.add(null);
                } else {
                    System.out.println(value);
                    list.add(value);
                }
            }
        }
        return list;
    }

    @Override
    public void save(Long id) {
        PointingDevice pointingDevice = new PointingDevice();
        pointingDevice.setRefId(id);
        pointingDevice.setNumberOfButtons(getParseResult().get(2));
        pointingDevice.setDescription(getParseResult().get(4));
        pointingDevice.setPointingType(getParseResult().get(0));
        logger.info("saving pointing device with Asset Id:{} --> ", pointingDevice.getId());
        customRepository.save(pointingDevice);
    }
}
