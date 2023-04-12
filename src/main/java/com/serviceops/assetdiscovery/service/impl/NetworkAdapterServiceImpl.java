package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.controller.NetworkAdapterController;
import com.serviceops.assetdiscovery.entity.NetworkAdapter;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.service.interfaces.NetworkAdapterService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

@Service
public class NetworkAdapterServiceImpl implements NetworkAdapterService {
    private final CustomRepository customRepository;
    private final Logger logger = LoggerFactory.getLogger(NetworkAdapterController.class);

    public NetworkAdapterServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }

    public static void setCommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        // command for parsing number pf network cards.

        // command for parsing information about the .
        commands.put("sudo lshw -C network", new String[]{});
        //        commands.put("ls /sys/class/net | wc -w", new String[]{});


        LinuxCommandExecutorManager.add(NetworkAdapter.class, commands);
    }

    public static List<String> getParseResult() {
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(NetworkAdapter.class);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String[]> result : stringMap.entrySet()) {
            String[] values = result.getValue();

            for (int i = 1;i<values.length;i++) {
                if (values[i].contains("vendor:")) {
                    list.add(values[i].substring(values[i].indexOf(":") + 1));
                }
                else if (values[i].contains("description")) {
                    list.add(values[i].substring(values[i].indexOf(":") + 1));
                }
                else if (values[i].contains("serial:")) {
                    list.add(values[i].substring(values[i].indexOf(":") + 1));
                }
                else if (values[i].contains("ip:")) {
                    list.add(values[i].substring(values[i].indexOf(":") + 1));
                }
            }
            System.out.println("=======================================================================================================================================================================================================================================================================================================================================================================================================================================");
        }
        return list;
    }

    @Override
    public void save(Long id) {
        NetworkAdapter networkAdapter = new NetworkAdapter();
        networkAdapter.setId(id);
        networkAdapter.setManufacturer(getParseResult().get(0));
        networkAdapter.setDescription(getParseResult().get(1));
        networkAdapter.setMacAddress(getParseResult().get(2));
        networkAdapter.setIpAddress(getParseResult().get(3));
        System.out.println(getParseResult().get(4));
        customRepository.update(networkAdapter);
    }
}
