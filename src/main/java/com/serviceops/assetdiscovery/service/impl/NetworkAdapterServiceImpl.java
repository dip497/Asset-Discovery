package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.controller.NetworkAdapterController;
import com.serviceops.assetdiscovery.entity.NetworkAdapter;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.NetworkAdapterRest;
import com.serviceops.assetdiscovery.service.interfaces.NetworkAdapterService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.NetworkAdapterOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

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

        // command for parsing information about the network card.
        commands.put("sudo lshw -C network", new String[]{});

        LinuxCommandExecutorManager.add(NetworkAdapter.class, commands);
    }

    public static List<String> getParseResult() {
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(NetworkAdapter.class);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String[]> result : stringMap.entrySet()) {
            String[] values = result.getValue();

            for (int i = 0; i < values.length; i++) {
                if (values[i].contains("vendor:")) {
                    list.add(values[i].substring(values[i].indexOf(":") + 1));
                }
                if (values[i].contains("description")) {
                    list.add(values[i].substring(values[i].indexOf(":") + 1));
                }
                if (values[i].contains("serial:")) {
                    list.add(values[i].substring(values[i].indexOf(":") + 1));
                }
                if (values[i].contains("ip:")) {
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
//        System.out.println(getParseResult().get(4)); //TODO

        customRepository.update(networkAdapter);

        logger.info("Saving Network adapter with id: ==> {}", id);
    }

    @Override
    public void delete(Long id) {
        customRepository.deleteById(NetworkAdapter.class, id, "refId");
        logger.info("Deleted Network adapter with id: ==> {}", id);
    }

    @Override
    public void update(NetworkAdapterRest networkAdapterRest) {
        NetworkAdapter networkAdapter = new NetworkAdapter();
        NetworkAdapterOps networkAdapterOps = new NetworkAdapterOps(networkAdapter, networkAdapterRest);
        customRepository.save(networkAdapterOps.restToEntity());
        logger.info("NetworkAdapter Updated with Asset Id ->{}", networkAdapter.getRefId());
    }

    @Override
    public NetworkAdapterRest findByResId(Long id) {
        Optional<NetworkAdapter> networkAdapterOptional = customRepository.findByColumn("refId", id, NetworkAdapter.class);
        NetworkAdapterRest networkAdapterRest = new NetworkAdapterRest();
        if (networkAdapterOptional.isPresent()) {
            NetworkAdapterOps networkAdapterOps = new NetworkAdapterOps(networkAdapterOptional.get(), networkAdapterRest);

            logger.info("Finding Network adapter with id: ==> {}", id);
            return networkAdapterOps.entityToRest();
        } else {
            throw new ResourceNotFoundException("NetworkAdapterRest", "id", Long.toString(id));
        }

    }

    @Override
    public List<NetworkAdapterRest> findAll() {
        List<NetworkAdapter> networkAdapterList = customRepository.findAll(NetworkAdapter.class);
        List<NetworkAdapterRest> networkAdapterRestList = new ArrayList<>();

        for (NetworkAdapter rest : networkAdapterList) {
            NetworkAdapterOps networkAdapterOps = new NetworkAdapterOps(rest, new NetworkAdapterRest());
            networkAdapterRestList.add(networkAdapterOps.entityToRest());

            logger.info("Finding Network adapter with id: ==> {}", rest.getId());
        }
        return networkAdapterRestList;
    }
}
