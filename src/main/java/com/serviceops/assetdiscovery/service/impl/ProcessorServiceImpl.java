package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.controller.ProcessorController;
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
        //command for parsing cpu name.
        commands.put(" lscpu | grep 'Model name:'", new String[]{});

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
        processor.setL2CacheSize(formatData(getParseResult().get(0)));
        processor.setL3CacheSize(formatData(getParseResult().get(1)));
        processor.setManufacturer(getParseResult().get(2));
        processor.setFamily(getParseResult().get(3));
        processor.setWidth(getParseResult().get(4));
        processor.setCpuSpeed(formatData(getParseResult().get(5)) + " MHz");
        processor.setCoreCount(getParseResult().get(6));
        processor.setL1CacheSize(getParseResult().get(7));
        processor.setProcessorName(formatData(getParseResult().get(9)));
        customRepository.save(processor);
    }
/*
    private String convertToMB(String Data){ //TODO convert to MB.
        String dataInMb = "";
        String newData = Data.substring(Data.indexOf(":"),Data.indexOf(" ")).trim();
        if (Data.contains("MiB") || Data.contains("MB")) {
            dataInMb =newData + "MB";
        }
        else if (Data.contains("K") || Data.contains("Kib")){
            dataInMb = newData + "KB";
        }

        return dataInMb;
    }*/

    private String formatData(String data) {
        return data.substring(data.indexOf(":") + 1).trim();
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
        Optional<Processor> optionalProcessor = customRepository.findByColumn("id", id, Processor.class);
        if (optionalProcessor.isPresent()) {
            List<ProcessorRest> processors = new ArrayList<>();
            ProcessorRest processorRest = new ProcessorRest();
            ProcessorOps processorOps = new ProcessorOps(optionalProcessor.get(), processorRest);
            processors.add(processorOps.entityToRest());
            logger.info("Processor found with id -->{}", id);
            return processors;
        } else {
            logger.error("Processor not found with id -->{}", id);
            throw new ResourceNotFoundException("Processor", "refId", String.valueOf(id));
        }
    }
}
