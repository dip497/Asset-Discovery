package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.controller.PointingDeviceController;
import com.serviceops.assetdiscovery.entity.MotherBoard;
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
    private PointingDeviceOps pointingDeviceOps;
    private CustomRepository customRepository;

    private static void setCommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        // Command for getting information about pointing devices.
        commands.put("sudo dmidecode -t 21", new String[]{});
        LinuxCommandExecutorManager.add(PointingDevice.class, commands);
    }

    private static List<String> getParseResult() {
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(MotherBoard.class);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String[]> result : stringMap.entrySet()) {
            String[] values = result.getValue();
            for (String value : values) {
                if (value.isEmpty()) {
                    list.add(null);
                } else {
                    list.add(value);
                }
            }
        }
        return list;
    }

    @Override
    public void save() {
        PointingDevice pointingDevice = new PointingDevice();
        List<String> parseResult = getParseResult();
        logger.debug("PointingDevice -->", parseResult.get(0));
        pointingDevice.setDescription(parseResult.get(0));

        customRepository.save(pointingDevice);
    }
}
