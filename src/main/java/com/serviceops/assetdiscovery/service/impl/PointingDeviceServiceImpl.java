package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.controller.PointingDeviceController;
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PointingDeviceServiceImpl implements PointingDeviceService {
    private final Logger logger = LoggerFactory.getLogger(PointingDeviceController.class);
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
                for (int i = 0; i < result.length; i++) {
                    String results = result[i];
                    switch (count) {
                        case 1:
                            if (results.contains("Buttons:")) {
                                parsedResult[i][j] = results.substring(results.indexOf("Buttons:\t") + "Buttons:\t".length()+1);
                                break;
                            }
                        case 2:
                            if (results.contains("Type")) {
                                parsedResult[i][j] = results.substring(results.indexOf("Type:") + "Type:".length());
                                break;
                            }
                        case 3:
                            if (results.contains("Interface")) {
                                parsedResult[i][j] = results.substring(results.indexOf("Interface:") + "Interface:".length());
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

    @Override
    public void save(Long id) {
        String[][] parsedResult = getParsedResults();
        List<PointingDevice> pointingDevices = customRepository.findAllByColumnName(PointingDevice.class,"refId",id);

        if(!pointingDevices.isEmpty()){
            if(pointingDevices.size()==parsedResult.length){
                for(PointingDevice pointingDevice: pointingDevices){
                    for(String[] updatePointIngDevice : parsedResult){
                        pointingDevice.setNumberOfButtons(updatePointIngDevice[1]);
                        pointingDevice.setPointingType(updatePointIngDevice[2]);
                        pointingDevice.setDescription(updatePointIngDevice[3]);
                        customRepository.save(pointingDevice);
                    }
                }
            }else{
                for(PointingDevice pointingDevice: pointingDevices){
                    customRepository.deleteById(Monitor.class,pointingDevice.getId(),"id");
                }
                savePointingDevice(id);
            }
        }else{
            savePointingDevice(id);
        }

    }

    @Override
    public void update(Long id, PointingDeviceRest pointingDeviceRest) {
        PointingDeviceOps pointingDeviceOps = new PointingDeviceOps(new PointingDevice(),pointingDeviceRest);
        logger.info("Updated PointingDevice with id --> {}",pointingDeviceRest.getId());
        customRepository.update(pointingDeviceOps.restToEntity(pointingDeviceRest));
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleted pointing device with id --> {}", id);
        customRepository.deleteById(PointingDevice.class,id,"id");
    }

    @Override
    public List<PointingDeviceRest> getPointingDevices(Long id) {
        List<PointingDevice> pointingDevices = customRepository.findAllByColumnName(PointingDevice.class,"refId",id);
        if (!pointingDevices.isEmpty()){
            List<PointingDeviceRest> pointingDeviceRestList = new ArrayList<>();
            for(PointingDevice pointingDevice: pointingDevices){
                PointingDeviceOps pointingDeviceOps = new PointingDeviceOps(pointingDevice, new PointingDeviceRest());
                pointingDeviceRestList.add(pointingDeviceOps.entityToRest(pointingDevice));
                logger.info("Fetched pointing device with id: --> {}" ,id);
            }
            return pointingDeviceRestList;
        }else{
            logger.info("Could not found pointing device with id: --> {}",id);
            throw new ResourceNotFoundException("Pointing device","refId",String.valueOf(id));
        }
    }

    private void savePointingDevice(Long id) {
        for(String[] updatePointIngDevice : getParsedResults()){
            PointingDevice pointingDevice = new PointingDevice();
            pointingDevice.setRefId(id);
            pointingDevice.setNumberOfButtons(updatePointIngDevice[1]);
            pointingDevice.setPointingType(updatePointIngDevice[2]);
            pointingDevice.setDescription(updatePointIngDevice[3]);

            logger.info("Saved pointing device with id: --> {}",id);
            customRepository.save(pointingDevice);
        }
    }
}
