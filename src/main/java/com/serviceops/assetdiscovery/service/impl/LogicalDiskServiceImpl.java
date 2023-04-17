package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.LogicalDisk;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.LogicalDiskRest;
import com.serviceops.assetdiscovery.service.interfaces.LogicalDiskService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.LogicalDiskOps;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LogicalDiskServiceImpl implements LogicalDiskService {

    private final CustomRepository customRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public LogicalDiskServiceImpl(CustomRepository customRepository){
        this.customRepository = customRepository;
        setcommands();
    }
    @Override
    @Transactional
    public void save(Long id){
        String[][] parseResults = parseResults();
        if(parseResults.length>0){
        List<LogicalDisk> logicalDisks = customRepository.findAllByColumnName(LogicalDisk.class,"refId",id);
        if(!logicalDisks.isEmpty()){
            if(logicalDisks.size()==parseResults.length){
                for(LogicalDisk logicalDisk : logicalDisks){
                    for(String[] updatedLogicalDisk : parseResults){
                        setLogicalDisk(logicalDisk,updatedLogicalDisk);
                        logger.info("Updated LogicalDisk with Asset Id->{}",id);
                        customRepository.update(logicalDisk);
                    }
                }
            }else{
                for(LogicalDisk logicalDisk: logicalDisks){
                    customRepository.deleteById(LogicalDisk.class,logicalDisk.getId(),"id");
                }
                for(String[] updatedLogicalDisk: parseResults){
                    LogicalDisk logicalDisk =new LogicalDisk();
                    logicalDisk.setRefId(id);
                    setLogicalDisk(logicalDisk,updatedLogicalDisk);
                    logger.info("Updated LogicalDisks with Asset Id->{}",id);
                    customRepository.save(logicalDisk);
                }
            }
            }
        else{
            for(String[] LogicalDisks: parseResults){
                LogicalDisk logicalDisk =new LogicalDisk();
                logicalDisk.setRefId(id);
                setLogicalDisk(logicalDisk,LogicalDisks);
                logger.info("Saved LogicalDisk with Asset Id->{}",id);
                customRepository.save(logicalDisk);
            }
        }
        }else {
            logger.info("No logical disk exist for this Asset -> {}",id);
        }

    }
    @Override
    public void deleteById(Long refId, Long id){
        if(customRepository.findByColumn("refId",refId,LogicalDisk.class).isPresent()) {

            if(customRepository.findByColumn("id",id,LogicalDisk.class).isPresent()) {
                customRepository.deleteById(LogicalDisk.class, id, "id");
                logger.info("Deleting LogicalDisk with id->{}", id);
            }else{
                logger.error("Logical Disk with Asset ->{} not exist",refId);
            }
        }
        else {
            logger.error("Logical Disk with Asset -> {} not found",refId);
        }

    }
    @Override
    public void update(Long refId, Long id, LogicalDiskRest logicalDiskRest){
        if(!customRepository.findAllByColumnName(LogicalDisk.class,"refId",refId).isEmpty()) {
            Optional<LogicalDisk> logicalDisk = customRepository.findByColumn("id", id, LogicalDisk.class);
            if(logicalDisk.isPresent()) {
                LogicalDiskOps logicalDiskOps = new LogicalDiskOps(logicalDisk.get(), logicalDiskRest);
                customRepository.save(logicalDiskOps.restToEntity());
                logger.info("logicalDisk Updated with Asset Id ->{}",refId);
            }else {
                logger.error("Logical Disk with Asset -> {} not found",refId);
                throw new ResourceNotFoundException("LogicalDisk","refId",String.valueOf(refId));
            }
        }else {
            logger.error("Logical Disks with Asset -> {} not exist");
            throw new ResourceNotFoundException("LogicalDisks","refId",String.valueOf(refId));
        }
    }

    @Override
    public List<LogicalDiskRest> getAllLogicalDisks(Long refId){
        List<LogicalDisk> logicalDisks = customRepository.findAllByColumnName(LogicalDisk.class,"refId",refId);
        if(!logicalDisks.isEmpty()){
            List<LogicalDiskRest> logicalDiskRests = new ArrayList<>();
            for(LogicalDisk logicalDisk : logicalDisks) {
                LogicalDiskOps logicalDiskOps = new LogicalDiskOps(logicalDisk,new LogicalDiskRest());
                logicalDiskRests.add(logicalDiskOps.entityToRest());
                logger.info("Retrieving Monitor of refId -> {}",refId);
            }
            return logicalDiskRests;
        }else{
            logger.info("LogicalDisk Component of refId ->{} does not exist",refId);
            return new ArrayList<>();
        }

    }
    private void setLogicalDisk(LogicalDisk logicalDisk,String[] data){

        logicalDisk.setDescription(data[0]);
        logicalDisk.setName(data[1]);
        logicalDisk.setSerialNumber(data[2]);
        logicalDisk.setSize(data[3]);
        logicalDisk.setFileSystemType(data[4]);
    }
    private void setcommands() {

        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        //command for fetching number of logical disks it have
        commands.put("sudo lshw -c volume | grep description | wc -l", new String[]{});
        //command for fetching volume data
        commands.put("sudo lshw -c volume", new String[]{});


        LinuxCommandExecutorManager.add(LogicalDisk.class, commands);

    }
    private String[][] parseResults() {
        Map<String, String[]> commandResults = LinuxCommandExecutorManager.get(LogicalDisk.class);

        String[] numberOfLogicalDisks = commandResults.get("sudo lshw -c volume | grep description | wc -l");

        if (numberOfLogicalDisks.length == 0) {

            return new String[][]{};
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
            String result ="";
            int j = 0;
            for (Map.Entry<String, String[]> commandResult : commandResults.entrySet()) {
                if (j == 0){ j++;}
                else {
                    String[] ans = commandResult.getValue();
                    int values =0;
                    boolean name_fetched =false;
                    boolean size_capa =false;
                    boolean serial_fetched = false;
                    int i=1;
                    if(ans[0].isEmpty())i=2;
                    for (; i < ans.length; i++) {
                            if(ans[i].contains("-volume")){
                                values++;
                                name_fetched=false;
                                size_capa = false;
                                serial_fetched=false;
                                continue;
                            }
                            if(ans[i].contains("description")) {
                                parsedResult[values][0] =ans[i].substring(ans[i].indexOf("description:") + "descripiton:".length());
                                continue;
                            }
                            if(ans[i].contains("name") && !name_fetched){
                                parsedResult[values][1]=ans[i].substring(ans[i].indexOf("name:") + "name:".length());
                                name_fetched = true;
                                continue;
                            }
                            if (ans[i].contains("serial")){
                                parsedResult[values][2]=ans[i].substring(ans[i].indexOf("serial:") + "serial:".length());
                                serial_fetched=true;
                                continue;
                            }
                            if(ans[i].contains("size") && !size_capa){
                                parsedResult[values][3]=ans[i].substring(ans[i].indexOf("size:") + "size:".length());
                                size_capa = true;
                                continue;
                            }
                            if(ans[i].contains("capacity") && !size_capa){
                                parsedResult[values][3] = ans[i].substring(ans[i].indexOf("capacity:") + "capacity:".length());
                                size_capa = true;
                                continue;
                            }
                            if(ans[i].contains("filesystem")){
                                String sub = ans[i].substring(ans[i].indexOf("filesystem"),ans[i].length());
                                if(sub.split(" ").length==1) {
                                    parsedResult[values][4] = sub.substring("filesystem=".length(),sub.length());
                                }else{
                                    parsedResult[values][4] = sub.substring("filesystem=".length(),sub.indexOf(" "));
                                }
                                continue;
                            } else if (ans[i].contains("mount.fstype")) {
                                String sub = ans[i].substring(ans[i].indexOf("mount.fstype"),ans[i].length());
                                if(sub.split(" ").length==1) {
                                    parsedResult[values][4] = sub.substring("mount.fstype=".length(),sub.length());
                                }else{
                                    parsedResult[values][4] = sub.substring("mount.fstype=".length(),sub.indexOf(" "));
                                }
                            }

                    }
                }
            }
            return parsedResult;
        }

    }

}

