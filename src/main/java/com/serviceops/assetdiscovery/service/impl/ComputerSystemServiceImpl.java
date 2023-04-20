package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.ComputerSystem;
import com.serviceops.assetdiscovery.entity.OS;
import com.serviceops.assetdiscovery.entity.Processor;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.ComputerSystemRest;
import com.serviceops.assetdiscovery.service.interfaces.ComputerSystemService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.ComputerSystemOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ComputerSystemServiceImpl implements ComputerSystemService {

    private final CustomRepository customRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ComputerSystemServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }

    @Override
    public void save(Long id) {
        Optional<ComputerSystem> savedComputerSystem =
                customRepository.findByColumn("id", id, ComputerSystem.class);
        List<String> parsedResults = getParseResults();
        if (savedComputerSystem.isPresent()) {
            ComputerSystem computerSystem = savedComputerSystem.get();
            setDetails(computerSystem, parsedResults);
            logger.info("Updated ComputerSystem with refId ->{}", id);
            customRepository.save(computerSystem);
        } else {
            ComputerSystem computerSystem = new ComputerSystem();
            computerSystem.setRefId(id);
            setDetails(computerSystem, parsedResults);
            logger.info("Saved ComputerSystem with refId ->{}", id);
            customRepository.save(computerSystem);
        }
    }

    @Override
    public List<ComputerSystemRest> get(Long refId) {
        Optional<ComputerSystem> optionalComputerSystem =
                customRepository.findByColumn("refId", refId, ComputerSystem.class);
        if (optionalComputerSystem.isPresent()) {
            logger.info("Retrieving Computer System of refId -> {}", refId);
            ComputerSystemOps computerSystemOps =
                    new ComputerSystemOps(optionalComputerSystem.get(), new ComputerSystemRest());
            ComputerSystemRest computerSystemRest = computerSystemOps.entityToRest();
            Optional<Processor> optionalProcessor =
                    customRepository.findByColumn("refId", refId, Processor.class);
            if (optionalProcessor.isPresent()) {
                computerSystemRest.setNumberOfProcessors(optionalProcessor.get().getCoreCount());
                computerSystemRest.setNumberOfLogicalProcessor(2 * optionalProcessor.get().getCoreCount());
            }
            Optional<OS> optionalOs = customRepository.findByColumn("refId", refId, OS.class);
            List<ComputerSystemRest> computerSystemRests = new ArrayList<>();
            computerSystemRests.add(computerSystemRest);
            return computerSystemRests;
        } else {
            logger.error("Computer System with Asset -> {} not found", refId);
            return List.of();
        }
    }

    @Override
    public void update(Long refId, ComputerSystemRest computerSystemRest) {
        Optional<ComputerSystem> optionalComputerSystem =
                customRepository.findByColumn("refId", refId, ComputerSystem.class);
        if (!optionalComputerSystem.isPresent()) {
            logger.error("Computer System with Asset -> {} not found", refId);
            throw new ResourceNotFoundException("ConputerSystem", "refId", String.valueOf(refId));
        } else {
            ComputerSystem computerSystem = optionalComputerSystem.get();
            ComputerSystemOps computerSystemOps = new ComputerSystemOps(computerSystem, computerSystemRest);
            logger.info("Updating computerSystemOps of AssetId -> {}", refId);
            customRepository.save(computerSystemOps.restToEntity());
        }
    }

    @Override
    public void deleteById(Long refId) {
        if (customRepository.findByColumn("refId", refId, ComputerSystem.class).isPresent()) {
            logger.info("Deleting Computer Sytem with refId -> {}", refId);
            customRepository.deleteById(ComputerSystem.class, refId, "refId");
        } else {
            logger.error("Computer System with Asset -> {} not found", refId);
            throw new ResourceNotFoundException("ComputerSystem", "refId", String.valueOf(refId));
        }

    }

    private void setDetails(ComputerSystem computerSystem, List<String> data) {
        //setting sata of computerSystem
        computerSystem.setUserName(data.get(0));
        computerSystem.setModelName(data.get(1));
        computerSystem.setSystemType(data.get(2));
        computerSystem.setUuid(data.get(3));
        computerSystem.setBootUpState(data.get(4));
        computerSystem.setManufacturer(data.get(5));
    }

    private void setCommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        //command to fetch username
        commands.put("sudo who | head -n1| cut -d' ' -f1", new String[] {});
        //command to fetch product name
        commands.put("sudo dmidecode -s system-product-name", new String[] {});
        //command to fetch os architecture
        commands.put("uname -m", new String[] {});
        //command to fetch uuid
        commands.put("sudo dmidecode -s system-uuid", new String[] {});
        //command to get bootup state
        commands.put("systemctl is-system-running", new String[] {});
        //command to get manufcturer of computerSystem
        commands.put("sudo dmidecode -s system-manufacturer", new String[] {});
        LinuxCommandExecutorManager.add(ComputerSystem.class, commands);
    }

    private List<String> getParseResults() {

        Map<String, String[]> commandResults = LinuxCommandExecutorManager.get(ComputerSystem.class);
        List<String> parsedResults = new ArrayList<>();

        for (Map.Entry<String, String[]> commandResult : commandResults.entrySet()) {
            boolean flag = false;
            String[] results = commandResult.getValue();

            for (String i : results) {
                if (!i.trim().isEmpty()) {
                    flag = true;
                    parsedResults.add(i);
                }
            }
            if (!flag) {
                parsedResults.add(null);
                flag = true;
            }
        }
        return parsedResults;
    }


}
