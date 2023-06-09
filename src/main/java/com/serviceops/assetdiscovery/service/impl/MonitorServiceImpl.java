package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Monitor;
import com.serviceops.assetdiscovery.exception.ComponentNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.MonitorRest;
import com.serviceops.assetdiscovery.service.interfaces.MonitorService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.MonitorOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class MonitorServiceImpl implements MonitorService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);
    private final CustomRepository customRepository;
    private final MonitorOps monitorOps;

    public MonitorServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        monitorOps = new MonitorOps();
        setCommands();
    }

    @Override
    public void save(long refId) {
        String[][] parsedResults = parseResults();
        if (parsedResults.length != 0) {
            List<Monitor> monitors = customRepository.findAllByColumn("refId", refId, Monitor.class);
            if (!monitors.isEmpty()) {
                if (monitors.size() == parsedResults.length) {
                    for (int i = 0; i < monitors.size(); i++) {
                        setMonitor(monitors.get(i), parsedResults[i]);
                        logger.info("Updated Monitor with Asset Id->{}", refId);
                        customRepository.save(monitors.get(i));
                    }
                } else {
                    for (Monitor monitor : monitors) {
                        customRepository.deleteById(Monitor.class, monitor.getId(), "id");
                    }
                    for (String[] updateMonitor : parsedResults) {
                        Monitor monitor = new Monitor();
                        monitor.setRefId(refId);
                        logger.info("Updated Monitor with Asset Id->{}", refId);
                        setMonitor(monitor, updateMonitor);
                        customRepository.save(monitor);
                    }
                }
            } else {
                for (String[] updateMonitor : parsedResults) {
                    Monitor monitor = new Monitor();
                    monitor.setRefId(refId);
                    setMonitor(monitor, updateMonitor);
                    logger.info("Saved Monitor with Asset Id->{}", refId);
                    customRepository.save(monitor);
                }
            }
        } else {
            logger.debug("No Monitor exist for Assset -> {}", refId);
        }
    }

    @Override
    public List<MonitorRest> findAllByRefId(long id) {
        List<Monitor> monitorsList = customRepository.findAllByColumn("refId", id, Monitor.class);
        if (!monitorsList.isEmpty()) {
            List<MonitorRest> monitorRestList = new ArrayList<>();
            for (Monitor monitor : monitorsList) {
                monitorRestList.add(monitorOps.entityToRest(monitor,new MonitorRest()));
                logger.info("Retrieving Monitor of refId -> {}", id);
            }
            return monitorRestList;
        } else {
            logger.info("Monitor Component of refId ->{} does not exist", id);
            return List.of();
        }

    }

    @Override
    public MonitorRest updateById(long refId, long id, MonitorRest monitorRest) {
        Map<String, Long> fields = new HashMap<>();
        fields.put("refId", refId);
        fields.put("id", id);
        List<Monitor> monitors = customRepository.findByColumns(fields, Monitor.class);
        if (!monitors.isEmpty()) {
            customRepository.save(monitorOps.restToEntity(monitors.get(0),monitorRest));
            logger.info("Monitor Updated with Asset Id ->{}", refId);
            return monitorRest;
        } else {
            logger.error("Monitor with Id -> {} & Asset Id -> {} not exist", id, refId);
            throw new ComponentNotFoundException("Monitor", "refId", refId);
        }
    }

    @Override
    public boolean deleteById(long refId, long id) {
        boolean isDeleted = customRepository.deleteById(Monitor.class, id, "id");
        if (isDeleted) {
            logger.info("Monitor with Id-> {} & Asset Id-> {}", id, refId);
        } else {
            logger.info("Monitor with Asset -> {} not found", refId);
        }
        return isDeleted;
    }

    private void setMonitor(Monitor monitor, String[] data) {
        //setting up monitors' data
        monitor.setDescription(data[1]);
        monitor.setManufacturer(data[2]);
    }

    private void setCommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        //command for getting number of monitors by counting number of decription
        commands.put("sudo lshw -c display | grep description | wc -l", new String[] {});

        //command for getting description of monitor
        commands.put("sudo lshw -c display | grep description", new String[] {});

        //     commands.put("sudo lshw -c display | grep description | sed 's/^.*description: *//' | sed 's/ *$//'",new String[]{});

        //command for getting manufacturer of monitor
        commands.put("sudo lshw -c display | grep vendor", new String[] {});
        //commands.put("sudo lshw -c display | grep vendor | sed 's/^.*vendor: *//' | sed 's/ *$//'",new String[]{});

        // Adding all the commands to the Main HasMap where the class Asset is the key for all the commands
        LinuxCommandExecutorManager.add(Monitor.class, commands);
    }

    private String[][] parseResults() {
        Map<String, String[]> commandResults = LinuxCommandExecutorManager.get(Monitor.class);
        String[] numberOfMonitors = commandResults.get("sudo lshw -c display | grep description | wc -l");
        if (numberOfMonitors.length == 0) {
            return new String[][] {};
        } else {
            Pattern pattern = Pattern.compile("(?<=\\s|^)\\d+(?=\\s|$)");
            int numberOfMonitor = 0;
            for (String numOfDesc : numberOfMonitors) {
                if (!numOfDesc.trim().isEmpty()) {
                    Matcher matcher = pattern.matcher(numOfDesc);
                    if (matcher.find()) {
                        String number = matcher.group();
                        numberOfMonitor = Integer.parseInt(number);
                        break;
                    }
                }
            }

            String[][] parsedResult = new String[numberOfMonitor][commandResults.size()];
            int j = 0;
            int count = 1;
            for (Map.Entry<String, String[]> commandResult : commandResults.entrySet()) {
                if (j == 0) {
                    j++;
                    continue;
                }
                String[] result = commandResult.getValue();
                if (numberOfMonitor < result.length) {
                    for (int i = 1; i < result.length; i++) {
                        result[i - 1] = result[i];
                    }
                }
                for (int i = 0; i < numberOfMonitor; i++) {
                    String results = result[i];
                    if (results.contains("description:")) {
                        parsedResult[i][j] =
                                results.substring(results.indexOf("description:") + "description:".length());
                        break;
                    }
                    if (results.contains("vendor")) {
                        parsedResult[i][j] =
                                results.substring(results.indexOf("vendor:") + "vendor:".length());
                        break;
                    }
                }
                j++;
            }
            return parsedResult;
        }

    }
}
