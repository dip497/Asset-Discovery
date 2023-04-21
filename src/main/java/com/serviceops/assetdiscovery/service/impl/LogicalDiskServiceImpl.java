package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.LogicalDisk;
import com.serviceops.assetdiscovery.exception.ComponentNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.LogicalDiskRest;
import com.serviceops.assetdiscovery.service.interfaces.LogicalDiskService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.UnitConverter;
import com.serviceops.assetdiscovery.utils.mapper.LogicalDiskOps;
import jakarta.transaction.Transactional;
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
public class LogicalDiskServiceImpl implements LogicalDiskService {

    private static final Logger logger = LoggerFactory.getLogger(LogicalDiskServiceImpl.class);
    private final CustomRepository customRepository;

    public LogicalDiskServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setcommands();
    }

    @Override
    public void save(long id) {
        String[][] parseResults = parseResults();
        if (parseResults.length > 0) {
            List<LogicalDisk> logicalDisks =
                    customRepository.findAllByColumnName(LogicalDisk.class, "refId", id);
            if (!logicalDisks.isEmpty()) {
                if (logicalDisks.size() == parseResults.length) {
                    for (int i = 0; i < logicalDisks.size(); i++) {
                        setLogicalDisk(logicalDisks.get(i), parseResults[i]);
                        logger.info("Updated LogicalDisk with Asset Id->{}", id);
                        customRepository.save(logicalDisks.get(i));
                    }
                } else {
                    for (LogicalDisk logicalDisk : logicalDisks) {
                        customRepository.deleteById(LogicalDisk.class, logicalDisk.getId(), "id");
                    }
                    for (String[] updatedLogicalDisk : parseResults) {
                        LogicalDisk logicalDisk = new LogicalDisk();
                        logicalDisk.setRefId(id);
                        setLogicalDisk(logicalDisk, updatedLogicalDisk);
                        logger.info("Updated LogicalDisks with Asset Id->{}", id);
                        customRepository.save(logicalDisk);
                    }
                }
            } else {
                for (String[] LogicalDisks : parseResults) {
                    LogicalDisk logicalDisk = new LogicalDisk();
                    logicalDisk.setRefId(id);
                    setLogicalDisk(logicalDisk, LogicalDisks);
                    logger.info("Saved LogicalDisk with Asset Id->{}", id);
                    customRepository.save(logicalDisk);
                }
            }
        } else {
            logger.info("No logical disk exist for this Asset -> {}", id);
        }

    }

    @Override
    public List<LogicalDiskRest> findAllByRefId(long refId) {
        List<LogicalDisk> logicalDisks =
                customRepository.findAllByColumnName(LogicalDisk.class, "refId", refId);
        if (!logicalDisks.isEmpty()) {
            List<LogicalDiskRest> logicalDiskRests = new ArrayList<>();
            for (LogicalDisk logicalDisk : logicalDisks) {
                LogicalDiskOps logicalDiskOps = new LogicalDiskOps(logicalDisk, new LogicalDiskRest());
                logicalDiskRests.add(logicalDiskOps.entityToRest());
                logger.info("Retrieving LogicalDisk of refId -> {}", refId);
            }
            return logicalDiskRests;
        } else {
            logger.info("LogicalDisk Component of refId ->{} does not exist", refId);
            return List.of();
        }

    }

    @Override
    public void updateById(long refId, long id, LogicalDiskRest logicalDiskRest) {
        Map<String, Long> fields = new HashMap<>();
        fields.put("refId", refId);
        fields.put("id", id);
        List<LogicalDisk> logicalDisks = customRepository.findByColumns(fields, LogicalDisk.class);
        if (!logicalDisks.isEmpty()) {
            LogicalDiskOps logicalDiskOps = new LogicalDiskOps(logicalDisks.get(0), logicalDiskRest);
            customRepository.save(logicalDiskOps.restToEntity());
            logger.info("logicalDisk Updated with Asset Id ->{}", refId);
        } else {
            logger.error("Logical Disks with id -> {} & Asset -> {} not exist", id, refId);
            throw new ComponentNotFoundException("LogicalDisks", "refId", refId);
        }
    }

    @Override
    public boolean deleteById(long refId, long id) {

        boolean isDeleted = customRepository.deleteById(LogicalDisk.class, id, "id");
        if (isDeleted) {
            logger.info("Logical Disk with Asset ->{} deleted", refId);
        } else {
            logger.info("Logical Disk with Asset -> {} not found", refId);
        }
        return isDeleted;
    }

    private void setLogicalDisk(LogicalDisk logicalDisk, String[] data) {
        logicalDisk.setDescription(data[0]);
        logicalDisk.setName(data[1]);
        logicalDisk.setSerialNumber(data[2]);
        logicalDisk.setSize(UnitConverter.convertToBytes(data[3]));
        logicalDisk.setFileSystemType(data[4]);
    }

    private void setcommands() {

        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        //command for fetching number of logical disks it
        commands.put("sudo lshw -c volume | grep description | wc -l", new String[] {});
        //command for fetching volume data
        commands.put("sudo lshw -c volume", new String[] {});


        LinuxCommandExecutorManager.add(LogicalDisk.class, commands);

    }

    private String[][] parseResults() {
        Map<String, String[]> commandResults = LinuxCommandExecutorManager.get(LogicalDisk.class);

        String[] numberOfLogicalDisks = commandResults.get("sudo lshw -c volume | grep description | wc -l");

        if (numberOfLogicalDisks.length == 0) {

            return new String[][] {};
        } else {
            Pattern pattern = Pattern.compile("(?<=\\s|^)\\d+(?=\\s|$)");
            int numberOfLogicalDisk = 0;
            for (String numOfDesc : numberOfLogicalDisks) {

                if (!numOfDesc.trim().isEmpty()) {

                    Matcher matcher = pattern.matcher(numOfDesc);

                    if (matcher.find()) {

                        String number = matcher.group();
                        numberOfLogicalDisk = Integer.parseInt(number);
                        break;

                    }
                }
            }
            String[][] parsedResult = new String[numberOfLogicalDisk][5];
            int j = 0;
            for (Map.Entry<String, String[]> commandResult : commandResults.entrySet()) {
                if (j == 0) {
                    j++;
                } else {
                    String[] ans = commandResult.getValue();
                    int values = 0;
                    boolean nameFetched = false;
                    boolean sizeCapa = false;
                    boolean serialFetched = false;
                    int removeEmptyString = 0;
                    for (; removeEmptyString < ans.length; removeEmptyString++) {
                        if (ans[removeEmptyString].contains("USB")) {
                            removeEmptyString++;
                            break;
                        }
                    }
                    for (int i = removeEmptyString; i < ans.length; i++) {
                        if (ans[i].contains("-volume")) {
                            values++;
                            nameFetched = false;
                            sizeCapa = false;
                            serialFetched = false;
                            continue;
                        }
                        if (ans[i].contains("description")) {
                            parsedResult[values][0] = ans[i].substring(
                                    ans[i].indexOf("description:") + "descripiton:".length());
                            continue;
                        }
                        if (ans[i].contains("logical name") && !nameFetched) {
                            parsedResult[values][1] =
                                    ans[i].substring(ans[i].indexOf("name:") + "name:".length());
                            nameFetched = true;
                            continue;
                        }
                        if (ans[i].contains("serial")) {
                            parsedResult[values][2] =
                                    ans[i].substring(ans[i].indexOf("serial:") + "serial:".length());
                            serialFetched = true;
                            continue;
                        }
                        if (ans[i].contains("size") && !sizeCapa) {
                            parsedResult[values][3] =
                                    ans[i].substring(ans[i].indexOf("size:") + "size:".length());
                            sizeCapa = true;
                            continue;
                        }
                        if (ans[i].contains("capacity") && !sizeCapa) {
                            parsedResult[values][3] =
                                    ans[i].substring(ans[i].indexOf("capacity:") + "capacity:".length());
                            sizeCapa = true;
                            continue;
                        }
                        if (ans[i].contains("filesystem")) {
                            String sub = ans[i].substring(ans[i].indexOf("filesystem"));
                            if (sub.split(" ").length == 1) {
                                parsedResult[values][4] = sub.substring("filesystem=".length());
                            } else {
                                parsedResult[values][4] =
                                        sub.substring("filesystem=".length(), sub.indexOf(" "));
                            }
                            continue;
                        } else if (ans[i].contains("mount.fstype")) {
                            String sub = ans[i].substring(ans[i].indexOf("mount.fstype"));
                            if (sub.split(" ").length == 1) {
                                parsedResult[values][4] = sub.substring("mount.fstype=".length());
                            } else {
                                parsedResult[values][4] =
                                        sub.substring("mount.fstype=".length(), sub.indexOf(" "));
                            }
                        }

                    }
                }
            }
            return parsedResult;
        }

    }

}

