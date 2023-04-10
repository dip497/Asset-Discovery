package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.ComputerSystem;
import com.serviceops.assetdiscovery.entity.Processor;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.ComputerSystemRest;
import com.serviceops.assetdiscovery.service.interfaces.ComputerSystemService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.ComputerSystemOps;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ComputerSystemServiceImpl implements ComputerSystemService {

    private final CustomRepository customRepository;
    public ComputerSystemServiceImpl(CustomRepository customRepository){
        this.customRepository = customRepository;
        setCommands();
    }
    @Override
    public void save(Long Id) {
        Optional<ComputerSystem> savedComputerSystem= customRepository.findByColumn("id", Id, ComputerSystem.class);
        List<String> parsedResults = getParseResults();
        if(savedComputerSystem.isPresent()) {
           ComputerSystem computerSystem = savedComputerSystem.get();
           computerSystem.setUserName(parsedResults.get(0));
           computerSystem.setModelName(parsedResults.get(1));
           computerSystem.setSystemType(parsedResults.get(2));
           computerSystem.setUuid(parsedResults.get(3));
           computerSystem.setBootUpState(parsedResults.get(4));
           computerSystem.setManufacturer(parsedResults.get(5));
           customRepository.save(computerSystem);
        }else{
            ComputerSystem computerSystem = new ComputerSystem();
            computerSystem.setRefId(Id);
            computerSystem.setUserName(parsedResults.get(0));
            computerSystem.setModelName(parsedResults.get(1));
            computerSystem.setSystemType(parsedResults.get(2));
            computerSystem.setUuid(parsedResults.get(3));
            computerSystem.setBootUpState(parsedResults.get(4));
            computerSystem.setManufacturer(parsedResults.get(5));
            customRepository.save(computerSystem);
        }
    }

    @Override
    public ComputerSystemRest getComputerSystem(Long id) {
        Optional<ComputerSystem> optionalComputerSystem = customRepository.findByColumn("refId", id, ComputerSystem.class);
        if (optionalComputerSystem.isPresent()) {
            ComputerSystemRest computerSystemRest = new ComputerSystemRest();
            Optional<Processor> optionalProcessor = customRepository.findByColumn("refId", id, Processor.class);
            if (optionalProcessor.isPresent()) {
                computerSystemRest.setNumberOfProcessors(1);
                computerSystemRest.setNumberOfLogicalProcessor(2 * optionalProcessor.get().getCoreCount());
                ComputerSystemOps computerSystemOps = new ComputerSystemOps(optionalComputerSystem.get(), computerSystemRest);
            }
            return computerSystemRest;
            }
        return null;
    }
    private void setCommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        commands.put("sudo who | head -n1| cut -d' ' -f1", new String[]{});
        commands.put("sudo dmidecode -s system-product-name", new String[]{});
        commands.put("uname -m", new String[]{});
        commands.put("sudo dmidecode -s system-uuid", new String[]{});
        commands.put("systemctl is-system-running", new String[]{});
        commands.put("sudo dmidecode -s system-manufacturer", new String[]{});
        LinuxCommandExecutorManager.add(ComputerSystem.class, commands);
    }
    private List<String> getParseResults() {
        Map<String, String[]> commandResults = LinuxCommandExecutorManager.get(ComputerSystem.class);
        List<String> parsedResults = new ArrayList<>();
        for (Map.Entry<String, String[]> commandResult : commandResults.entrySet()) {
            String[] results = commandResult.getValue();
            for(String i:results){
                parsedResults.add(i);
            }
        }
        return parsedResults;
    }
    @Override
    public void deleteComputerSystemById(Long id){
        customRepository.deleteById(ComputerSystem.class,id,"refId");
    }


}
