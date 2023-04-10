package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.ComputerSystem;
import com.serviceops.assetdiscovery.entity.Processor;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.ComputerSystemRest;
import com.serviceops.assetdiscovery.service.interfaces.ComputerSystemService;
import com.serviceops.assetdiscovery.utils.mapper.ComputerSystemOps;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerSystemServiceImpl implements ComputerSystemService {

    private ComputerSystemOps computerSystemOps;
    private CustomRepository customRepository;
    public ComputerSystemServiceImpl(CustomRepository customRepository){
        this.customRepository = customRepository;
        computerSystemOps.setCommands();
    }
    @Override
    public void save(Long Id) {
        List<String> parsedResults = ComputerSystemOps.getParseResults();
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

    @Override
    public ComputerSystemRest getComputerSystem(Long id) {
        Optional<ComputerSystem> optionalComputerSystem = customRepository.findByColumn("refId", id, ComputerSystem.class);
        if (optionalComputerSystem.isPresent()) {
            ComputerSystemRest computerSystemRest = new ComputerSystemRest();
            Optional<Processor> optionalProcessor = customRepository.findByColumn("ref_id", id, Processor.class);
            if (optionalProcessor.isPresent()) {
                computerSystemRest.setNumberOfProcessors(1);
                computerSystemRest.setNumberOfLogicalProcessor(2 * optionalProcessor.get().getCoreCount());
                computerSystemOps = new ComputerSystemOps(optionalComputerSystem.get(), computerSystemRest);
            }
            return computerSystemRest;
            }
        return null;
        }
    @Override
    public void deleteComputerSystemById(Long id){
        customRepository.deleteById(ComputerSystem.class,id,"ref_id");
    }


}
