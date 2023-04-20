package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Monitor;
import com.serviceops.assetdiscovery.entity.PointingDevice;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.PointingDeviceRest;
import com.serviceops.assetdiscovery.service.interfaces.PointingDeviceService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.PointingDeviceOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PointingDeviceServiceImpl implements PointingDeviceService {
    private static final Logger logger = LoggerFactory.getLogger(PointingDeviceServiceImpl.class);
    private final CustomRepository customRepository;

    public PointingDeviceServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }

    private static void setCommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        //command for getting number of pointing device by counting number of buttons
        commands.put("sudo dmidecode -t 21 | grep -i Buttons: | wc -l", new String[]{});
        // Command for getting information about pointing device button.
        commands.put("sudo dmidecode -t 21 | grep -i Buttons: ", new String[]{});
        // Command for getting information about pointing device Type.
        commands.put("sudo dmidecode -t 21 | grep -i Type: ", new String[]{});
        // Command for getting information about pointing device interface.
        commands.put("sudo dmidecode -t 21 | grep -i Interface: ", new String[]{});
        // Command for vendor information.
        commands.put("sudo lshw -C input | grep vendor:", new String[]{});
        LinuxCommandExecutorManager.add(PointingDevice.class, commands);
    }

    private String[][] getParsedResults() {
        Map<String, String[]> commandResults = LinuxCommandExecutorManager.get(PointingDevice.class);
        String[] numberOfPointingDevices = commandResults.get("sudo dmidecode -t 21 | grep -i Buttons: | wc -l");
        if (numberOfPointingDevices.length == 0) {
            return new String[][]{};
        } else {
            Pattern pattern = Pattern.compile("(?<=\\s|^)\\d+(?=\\s|$)");

            int numberOfPointingDevice = 0;
            for (int i = 0; i < numberOfPointingDevices.length; i++) {
                Matcher matcher = pattern.matcher(numberOfPointingDevices[0]);
                if (matcher.find()) {
                    String number = matcher.group();
                    numberOfPointingDevice = Integer.parseInt(number);
                }
            }
            String[][] parsedResult = new String[numberOfPointingDevice][commandResults.size()];
            int j = 0;
            int count = 1;
            for (Map.Entry<String, String[]> commandResult : commandResults.entrySet()) {
                if (j == 0) {
                    j++;
                    continue;
                }
                String[] result = commandResult.getValue();
                if (numberOfPointingDevice != result.length) {
                    for (int i = 1; i < result.length; i++) {
                        result[i - 1] = result[i];
                    }
                }
                for (int i = 0; i < numberOfPointingDevice && i < result.length; i++) {
                    String results = result[i];
                    switch (count) {
                        case 1:
                            if (results.contains("Buttons:")) {
                                parsedResult[i][j] = formatData(results,"Buttons:");
                                break;
                            }
                        case 2:
                            if (results.contains("Type")) {
                                parsedResult[i][j] = formatData(results,"Type:");
                                break;
                            }
                        case 3:
                            if (results.contains("Interface")) {
                                parsedResult[i][j] = formatData(results,"Interface:");
                                break;
                            }
                        case 4:
                            if (results.contains("vendor")) {
                                parsedResult[i][j] = formatData(results,"vendor:");
                                break;
                            }
                    }
                }
                count++;
                j++;
            }
            return parsedResult;
        }
    }

    private String formatData(String data, String keyWord){
        return data.substring(data.indexOf(keyWord) + keyWord.length()).trim();
    }

    @Override
    public void save(long refId) {
        String[][] parsedResult = getParsedResults();
        List<PointingDevice> pointingDevices = customRepository.findAllByColumnName(PointingDevice.class, "refId", refId);

        if (!pointingDevices.isEmpty()) {
            if (pointingDevices.size() == parsedResult.length) {
                for (PointingDevice pointingDevice : pointingDevices) {
                    for (String[] updatePointIngDevice : parsedResult) {
                        pointingDevice.setNumberOfButtons(Integer.parseInt(updatePointIngDevice[1].trim()));
                        pointingDevice.setPointingType(updatePointIngDevice[2]);
                        pointingDevice.setDescription(updatePointIngDevice[3]);
                        pointingDevice.setManufacturer(updatePointIngDevice[4]);
                        customRepository.save(pointingDevice);
                        logger.info("Updated pointing device with refId: --> {}", pointingDevice.getId());
                    }
                }
            } else {
                for (PointingDevice pointingDevice : pointingDevices) {
                    customRepository.deleteById(Monitor.class, pointingDevice.getId(), "id");
                }
                savePointingDevice(refId);
            }
        } else {
            savePointingDevice(refId);
        }

    }

    @Override
    public PointingDeviceRest update(long refId, PointingDeviceRest pointingDeviceRest) {
        Optional<PointingDevice> optionalPointingDevice = customRepository.findByColumn("refid", refId, PointingDevice.class);
        if (optionalPointingDevice.isPresent()) {
            PointingDevice pointingDevice = optionalPointingDevice.get();
            PointingDeviceOps pointingDeviceOps = new PointingDeviceOps(pointingDevice, pointingDeviceRest);
            logger.info("Updated PointingDevice with id --> {}", pointingDeviceRest.getId());
            customRepository.save(pointingDeviceOps.restToEntity(pointingDeviceRest));
            return pointingDeviceRest;
        } else {
            logger.info("Could not found pointing device with id: --> {}", refId);
            throw new ResourceNotFoundException("Pointing device", "refId", refId);
        }
    }

    @Override
    public void deleteById(long refId) {
        logger.info("Deleted pointing device with id --> {}", refId);
        customRepository.deleteById(PointingDevice.class, refId, "id");
    }

    @Override
    public List<PointingDeviceRest> getPointingDevices(long refId) {
        List<PointingDevice> pointingDevices = customRepository.findAllByColumnName(PointingDevice.class, "refId", refId);
        if (!pointingDevices.isEmpty()) {
            List<PointingDeviceRest> pointingDeviceRestList = new ArrayList<>();
            if (!pointingDevices.isEmpty()) {
                for (PointingDevice pointingDevice : pointingDevices) {
                    PointingDeviceOps pointingDeviceOps = new PointingDeviceOps(pointingDevice, new PointingDeviceRest());
                    pointingDeviceRestList.add(pointingDeviceOps.entityToRest());
                    logger.info("Fetched pointing device with id: --> {}", refId);
                }
            } else {
                logger.info("Could not found pointing device with id: --> {}", refId);
                pointingDeviceRestList.add(new PointingDeviceRest());
            }
            return pointingDeviceRestList;
        }
        return List.of();
    }

    private void savePointingDevice(long refId) {
        for (String[] updatePointIngDevice : getParsedResults()) {
            PointingDevice pointingDevice = new PointingDevice();
            pointingDevice.setRefId(refId);
            try {

                pointingDevice.setNumberOfButtons(Integer.parseInt(updatePointIngDevice[1].trim()));
                pointingDevice.setPointingType(updatePointIngDevice[2]);
                pointingDevice.setDescription(updatePointIngDevice[3]);
                pointingDevice.setManufacturer(updatePointIngDevice[4]);

                logger.info("Saved pointing device with id: --> {}", refId);
                customRepository.save(pointingDevice);
            } catch (IndexOutOfBoundsException e) {
                customRepository.save(pointingDevice);
            }
        }
    }
}
