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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        commands.put("sudo lshw -C network | grep description | wc -l", new String[]{});
        // command for parsing information about description of the network card.
        commands.put("sudo lshw -C network | grep description", new String[]{});

        // command for parsing information about vendor of the network card.
        commands.put("sudo lshw -C network | grep vendor", new String[]{});
        // command for parsing information about MAC address of the network card.
        commands.put("sudo lshw -C network | grep serial", new String[]{});
        // command for parsing information about IP address of the network card.
        commands.put("sudo ip address show | awk '/inet / {print $2}'", new String[]{});
//        commands.put("sudo lshw -C network | grep ip", new String[]{});
        // command for parsing information about IP subnet mask of the network card.
        commands.put("sudo ifconfig | grep netmask  | awk '{print $4}' | cut -f1 -d'/'", new String[]{});

        LinuxCommandExecutorManager.add(NetworkAdapter.class, commands);
    }

    private String[][] getParseResult() {
        Map<String, String[]> commandResults = LinuxCommandExecutorManager.get(NetworkAdapter.class);
        String[] numberOfNetworkAdapter = commandResults.get("sudo lshw -C network | grep description | wc -l");
        if (numberOfNetworkAdapter.length == 0) {
            return new String[][]{};
        } else {
            Pattern pattern = Pattern.compile("(?<=\\s|^)\\d+(?=\\s|$)");
            int numberOfNetworkAdapters = 0;
            for (int i = 0; i < numberOfNetworkAdapter.length; i++) {
                Matcher matcher = pattern.matcher(numberOfNetworkAdapter[0]);
                if (matcher.find()) {
                    String number = matcher.group();
                    numberOfNetworkAdapters = Integer.parseInt(number);
                }
            }
            String[][] parseResult = new String[numberOfNetworkAdapters][commandResults.size()];
            int j = 0;
            int count = 1;
            for (Map.Entry<String, String[]> commandResult : commandResults.entrySet()) {
                if (j == 0) {
                    j++;
                    continue;
                }
                String[] result = commandResult.getValue();
                if(numberOfNetworkAdapters!=result.length){
                    for(int i=1;i<result.length;i++){
                        result[i-1]=result[i];
                    }
                }
                for (int i = 0; i < numberOfNetworkAdapters; i++) {
                    String results = result[i];
                    switch (count) {
                        case 1:
                            if (results.contains("description:")) {
                                parseResult[i][j] = results.substring(results.indexOf("description:") + "description:".length());
                                break;
                            }
                        case 2:
                            if (results.contains("vendor:")) {
                                parseResult[i][j] = results.substring(results.indexOf("vendor:") + "vendor:".length());
                                break;
                            }
                        case 3:
                            if (results.contains("serial:")) {
                                parseResult[i][j] = results.substring(results.indexOf("serial:") + "serial:".length());
                                break;
                            } else {
                                parseResult[i][j] = result[i];
                            }
                        case 4:
                            if (results.contains(".")) {
                                parseResult[i][j] = results;
                                break;
                            }
//
                        case 5:
                            if (results.contains("255")) {
                                parseResult[i][j] = results;
                                break;
                            }
                    }
                }
                count++;
                j++;
            }
            return parseResult;
        }
    }

    @Override
    public void save(Long id) {
        String[][] parseResult = getParseResult();
        List<NetworkAdapter> networkAdapters = customRepository.findAllByColumnName(NetworkAdapter.class, "refId", id);
        if (!networkAdapters.isEmpty()) {
            if (networkAdapters.size() == parseResult.length) {
                for (NetworkAdapter networkAdapter : networkAdapters) {
                    for (String[] updateNetworkAdapter : parseResult) {
                        networkAdapter.setDescription(updateNetworkAdapter[1]);
                        networkAdapter.setManufacturer(updateNetworkAdapter[2]);
                        networkAdapter.setMacAddress(updateNetworkAdapter[3]);
                        customRepository.save(networkAdapter);
                    }
                }
            } else {
                for (NetworkAdapter networkAdapter : networkAdapters) {
                    customRepository.deleteById(NetworkAdapter.class, networkAdapter.getId(), "id");
                }
                logger.info("NetworkAdapter with id : --> {}",id);
                saveNetworkAdapter(id, parseResult);
            }
        } else {

            saveNetworkAdapter(id, parseResult);
        }

        logger.info("Saving Network adapter with id: ==> {}", id);
    }

    private void saveNetworkAdapter(Long id, String[][] parseResult) {
        for (String[] updateNetworkAdapter : parseResult) {
            NetworkAdapter networkAdapter = new NetworkAdapter();
            networkAdapter.setRefId(id);
            networkAdapter.setDescription(updateNetworkAdapter[1]);
            networkAdapter.setManufacturer(updateNetworkAdapter[2]);
            networkAdapter.setMacAddress(updateNetworkAdapter[3]);
            networkAdapter.setIpAddress(updateNetworkAdapter[4]);
            networkAdapter.setIpSubnet(updateNetworkAdapter[5]);
            customRepository.save(networkAdapter);
        }
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
        customRepository.update(networkAdapterOps.restToEntity());
        logger.info("NetworkAdapter Updated with Asset Id ->{}", networkAdapter.getRefId());
    }

    @Override
    public NetworkAdapterRest findByResId(Long id) {
        Optional<NetworkAdapter> networkAdapterOptional = customRepository.findByColumn("id", id, NetworkAdapter.class);
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
    public List<NetworkAdapterRest> findAll(Long refId) {
        List<NetworkAdapter> networkAdapterList = customRepository.findAll(NetworkAdapter.class);

        if (!networkAdapterList.isEmpty()){
            List<NetworkAdapterRest> networkAdapterRestList = new ArrayList<>();
            for (NetworkAdapter networkAdapter : networkAdapterList) {
                NetworkAdapterOps networkAdapterOps = new NetworkAdapterOps(networkAdapter, new NetworkAdapterRest());
                networkAdapterRestList.add(networkAdapterOps.entityToRest());
                logger.info("Finding Network adapter with id: ==> {}", networkAdapter.getId());
            }
            return networkAdapterRestList;

        } else {
            logger.error("Could not find NetworkAdapter with id: --> {}",refId);
            throw new ResourceNotFoundException("NetworkAdapterRest", "refId", Long.toString(refId));
        }
    }
}
