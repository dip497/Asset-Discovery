package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Processor;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.ProcessorRest;
import com.serviceops.assetdiscovery.service.interfaces.ProcessorService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.ProcessorOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProcessorServiceImpl implements ProcessorService {
    private final CustomRepository customRepository;
    private final Logger logger = LoggerFactory.getLogger(ProcessorServiceImpl.class);

    public ProcessorServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }

    private void setCommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        // command for parsing information about the L2 cache.
        commands.put("lscpu | grep 'L2 cache:' | awk '{print $(NF-1), $NF}'", new String[]{});
//        commands.put("lscpu | grep 'L2 cache:' | awk '{print $(NF-1), $NF}'", new String[]{});
        // command for parsing information about the L3 cache.
        commands.put("lscpu | grep 'L3 cache' | awk '{print $(NF-1), $NF}'", new String[]{}); //TODO L1 cache
        // command for parsing information about the manufacturer.
        commands.put("lscpu | grep 'Vendor ID:' | awk '{print $NF}'", new String[]{});
        // command for parsing information about the cpu family.
        commands.put("lscpu | grep 'CPU family:' | awk '{print $NF}'", new String[]{});
        // command for parsing information about the cpu width.
        commands.put("lscpu | grep 'CPU op-mode(s):' | awk '{print $NF}'", new String[]{});
        // command for parsing information about the cpu speed.
        commands.put("lscpu | grep 'CPU MHz:' | awk '{print $(NF-1), $NF}'", new String[]{});

        // command for parsing information about the cpu core count.
        commands.put("lscpu | grep 'CPU(s):' | awk '{print $2F}'", new String[]{});
        // command for parsing information about the L1 cache.
        commands.put("lscpu | grep 'L1i cache:' | awk '{print $(NF-1), $NF}'", new String[]{});
        commands.put("lscpu | grep 'L1d cache:' | awk '{print $(NF-1), $NF}'", new String[]{});
        //command for parsing cpu name.
        commands.put("lscpu | grep 'Model name: '", new String[]{});

        LinuxCommandExecutorManager.add(Processor.class, commands);
    }

    private List<String> getParseResult() {
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(Processor.class);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String[]> result : stringMap.entrySet()) {
            String[] values = result.getValue();
            Collections.addAll(list, values);
        }
        return list;
    }

    @Override
    public void save(Long id) {

        Optional<Processor> fetchProcessor = customRepository.findByColumn("refId", id, Processor.class);
        if (fetchProcessor.isPresent()) {
            Processor processor = fetchProcessor.get();
            logger.info("Processor updated with id -->{}", id);
            setData(processor);
        } else {
            Processor processor = new Processor();
            processor.setRefId(id);
            logger.info("Processor saved with id: --> {}", id);
            setData(processor);
        }
    }

    private void setData(Processor processor) {
        try {
            processor.setL2CacheSize(convertToBaseUnit(getParseResult().get(0)));
            processor.setL3CacheSize(convertToBaseUnit(getParseResult().get(1)));
            processor.setManufacturer(getParseResult().get(2));
            processor.setFamily(convertToBaseUnit(getParseResult().get(3)));
            processor.setWidth(getParseResult().get(4));
            processor.setCpuSpeed(convertToBaseUnit(getParseResult().get(5)));
            processor.setCoreCount(convertToBaseUnit(getParseResult().get(6)));
            processor.setL1CacheSize(!getParseResult().get(8).isEmpty() ? convertToBaseUnit(getParseResult().get(8)) : convertToBaseUnit(getParseResult().get(9)));
            processor.setProcessorName(getParseResult().get(10).substring(getParseResult().get(10).indexOf(": ") + 2).trim());
            customRepository.save(processor);
        } catch (IndexOutOfBoundsException e) {
            logger.error("index out of bound exception in Processor with id -->{}", processor.getId());
            customRepository.save(processor);
        }
    }

    private long convertToBaseUnit(String Data) {
        long data;
        Data = Data.toLowerCase();
        if (Data.contains("mib") || Data.contains("mb")) {
            Data = Data.replaceAll("[^0-9]", "").trim();
            Data = Data.replaceAll(" + ", "");
            data = Long.parseLong(Data) * 1024 * 1024;

        } else if (Data.contains("k") || Data.contains("kib")) {
            Data = Data.replaceAll("[^0-9]", "").trim();
            Data = Data.replaceAll(" + ", "");
            data = Long.parseLong(Data) * 1024;
        } else if (Data.contains("mhz")) {
            Data = Data.substring(0, Data.indexOf("."));
            Data = Data.replaceAll("[^0-9]", "").trim();
            data = (long) Double.parseDouble(Data) * 1000 * 1000;
        } else {
            return Long.parseLong(Data);
        }

        return data;
    }

    @Override
    public void update(Long id, ProcessorRest processorRest) {

        Optional<Processor> optionalProcessor = customRepository.findByColumn("refId", id, Processor.class);

        if (optionalProcessor.isPresent()) {
            Processor processor = optionalProcessor.get();
            ProcessorOps processorOps = new ProcessorOps(processor, processorRest);
            customRepository.save(processorOps.restToEntity());
            logger.info("Processor updated with id -->{}", id);
        } else {
            logger.error("Processor not found with id -->{}", id);
            throw new ResourceNotFoundException("Processor", "refId", String.valueOf(id));
        }

    }

    @Override
    public void deleteById(Long id) {
        logger.info("Processor deleted with id -->{}", id);
        customRepository.deleteById(Processor.class, id, "id");
    }

    @Override
    public List<ProcessorRest> findByRefId(Long id) {
        Optional<Processor> optionalProcessor = customRepository.findByColumn("refId", id, Processor.class);
        List<ProcessorRest> processors = new ArrayList<>();
        if (optionalProcessor.isPresent()) {
            ProcessorRest processorRest = new ProcessorRest();
            ProcessorOps processorOps = new ProcessorOps(optionalProcessor.get(), processorRest);
            processors.add(processorOps.entityToRest());
            logger.info("Processor found with id -->{}", id);

        } else {
            logger.error("Processor not found with id -->{}", id);
            processors.add(new ProcessorRest());
        }
        return processors;
    }
}
