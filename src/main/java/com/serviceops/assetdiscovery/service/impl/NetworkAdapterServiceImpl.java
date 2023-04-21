package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.NetworkAdapter;
import com.serviceops.assetdiscovery.exception.ComponentNotFoundException;
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
    private static final Logger logger = LoggerFactory.getLogger(NetworkAdapterServiceImpl.class);

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
                if (numberOfNetworkAdapters != result.length) {
                    for (int i = 1; i < result.length; i++) {
                        result[i - 1] = result[i];
                    }
                }
                for (int i = 0; i < numberOfNetworkAdapters && i < result.length; i++) {
                    String results = result[i];
                    switch (count) {
                        case 1:
                            if (results.contains("description:")) {
                                parseResult[i][j] = parseData(results, "description:");
                                break;
                            }
                        case 2:
                            if (results.contains("vendor:")) {
                                parseResult[i][j] = parseData(results, "vendor:");
                                break;
                            }
                        case 3:
                            if (results.contains("serial:")) {
                                parseResult[i][j] = parseData(results, "serial:");
                                break;
                            } else {
                                parseResult[i][j] = result[i];
                            }
                        case 4:
                            if (results.contains(".")) {
                                parseResult[i][j] = results;
                                break;
                            }

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

    private String parseData(String output, String keyWord) {
        return output.substring(output.indexOf(keyWord) + keyWord.length()).trim();
    }

    @Override
    public void save(long refId) {
        String[][] parseResult = getParseResult();
        List<NetworkAdapter> networkAdapters = customRepository.findAllByColumn( "refId", refId,NetworkAdapter.class);
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
                logger.info("NetworkAdapter with id : --> {}", refId);
                saveNetworkAdapter(refId, parseResult);
            }
        } else {

            saveNetworkAdapter(refId, parseResult);
        }

        logger.info("Saving Network adapter with id: ==> {}", refId);
    }

    private void saveNetworkAdapter(long refId, String[][] parseResult) {
        for (String[] updateNetworkAdapter : parseResult) {
            NetworkAdapter networkAdapter = new NetworkAdapter();
            try {

                networkAdapter.setRefId(refId);
                networkAdapter.setDescription(updateNetworkAdapter[1]);
                networkAdapter.setManufacturer(updateNetworkAdapter[2]);
                networkAdapter.setMacAddress(updateNetworkAdapter[3]);
                networkAdapter.setIpAddress(updateNetworkAdapter[4]);
                networkAdapter.setIpSubnet(updateNetworkAdapter[5]);
                customRepository.save(networkAdapter);
            } catch (IndexOutOfBoundsException e) {
                customRepository.save(networkAdapter);
                logger.info("index out of bound exception in NetworkAdapter with id : --> {}", refId);
            }
        }
    }

    @Override
    public boolean deleteById(long refId, long id) {
        boolean isDeleted = customRepository.deleteById(NetworkAdapter.class, id, "id");
        if (isDeleted) {
            logger.info("Deleting Network Adapter with refId--> {} and id->{}", refId, id);
        } else {
            logger.info("Deleting Network Adapter with refId--> {} and id->{}", refId, id);
        }
        return isDeleted;
    }

    @Override
    public NetworkAdapterRest updateById(NetworkAdapterRest networkAdapterRest, long refId, long id) {

        Map<String, Long> fields = new HashMap<>();
        fields.put("id", id);
        fields.put("refId", refId);

        List<NetworkAdapter> networkAdapters = customRepository.findByColumns(fields, NetworkAdapter.class);

        if (networkAdapters.isEmpty()) {
            logger.info("Could not found NetworkAdapter with id --> {} and refId -> {} ", id, refId);
            throw new ComponentNotFoundException("Network adapter", "id", id);
        } else {
            NetworkAdapterOps networkAdapterOps = new NetworkAdapterOps(networkAdapters.get(0), networkAdapterRest);
            customRepository.save(networkAdapterOps.restToEntity());
            logger.info("NetworkAdapter Updated with Asset Id ->{}", networkAdapterRest.getRefId());
            return networkAdapterOps.entityToRest();
        }
    }

    @Override
    public List<NetworkAdapterRest> findAllByRefId(Long refId) {
        List<NetworkAdapter> networkAdapterList = customRepository.findAllByColumn( "refId", refId,NetworkAdapter.class);
        List<NetworkAdapterRest> networkAdapterRestList = new ArrayList<>();
        if (!networkAdapterList.isEmpty()) {
            for (NetworkAdapter networkAdapter : networkAdapterList) {
                NetworkAdapterOps networkAdapterOps = new NetworkAdapterOps(networkAdapter, new NetworkAdapterRest());
                networkAdapterRestList.add(networkAdapterOps.entityToRest());
                logger.info("Fetched Network adapter with id: --> {}", refId);
            }
        }
        return networkAdapterRestList;
    }


}
