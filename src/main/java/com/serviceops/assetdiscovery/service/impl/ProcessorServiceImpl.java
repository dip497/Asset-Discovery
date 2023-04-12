package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.controller.ProcessorController;
import com.serviceops.assetdiscovery.entity.Processor;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.service.interfaces.ProcessorService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProcessorServiceImpl implements ProcessorService {
    private final CustomRepository customRepository;
    private final Logger logger = LoggerFactory.getLogger(ProcessorController.class);

    public ProcessorServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }
    public static void setCommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        // command for parsing information about the L2 cache.
        commands.put("lscpu | grep 'L2 cache' | awk '{print $NF}'", new String[]{});
        // command for parsing information about the L3 cache.
        commands.put("lscpu | grep 'L3 cache' | awk '{print $NF}'", new String[]{}); //TODO L1 cache
        // command for parsing information about the Architecture.
        commands.put("lscpu | grep 'Architecture:' | awk '{print $NF}'", new String[]{});
        // command for parsing information about the manufacturer.
        commands.put("lscpu | grep 'Vendor ID:' | awk '{print $NF}'", new String[]{});
        // command for parsing information about the cpu family.
        commands.put("lscpu | grep 'CPU family:' | awk '{print $NF}'", new String[]{});
        // command for parsing information about the cpu width.
        commands.put("lscpu | grep 'CPU op-mode(s):' | awk '{print $NF}'", new String[]{});
        // command for parsing information about the cpu speed.
        commands.put("lscpu | grep 'CPU MHz:' | awk '{print $NF}'", new String[]{});

        // command for parsing information about the cpu core count.
        commands.put("lscpu | grep 'CPU(s):' | awk '{print $2F}'", new String[]{});

        LinuxCommandExecutorManager.add(Processor.class, commands);
    }

    public static List<String> getParseResult() {
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(Processor.class);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String[]> result : stringMap.entrySet()) {
            String[] values = result.getValue();
            for (int i = 0; i < values.length; i++) {
                list.add(values[i]);
            }
        }
        return list;
    }
    @Override
    public void save(Long id) {
        Processor processor = new Processor();
        processor.setId(id);
        processor.setL2CacheSize(getParseResult().get(0));
        processor.setL3CacheSize(getParseResult().get(1));

        processor.setManufacturer(getParseResult().get(3));
        processor.setFamily(getParseResult().get(4));
        processor.setWidth(getParseResult().get(5));
        processor.setCpuSpeed(getParseResult().get(6));
        processor.setCoreCount(getParseResult().get(7));

        customRepository.save(processor);
    }
}
