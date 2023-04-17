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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        List<LogicalDisk> logicalDisks = customRepository.findAllByColumnName(LogicalDisk.class,"refId",id);
        if(!logicalDisks.isEmpty()){
            if(logicalDisks.size()==parseResults.length){
                for(LogicalDisk logicalDisk : logicalDisks){
                    for(String[] updatedLogicalDisk : parseResults){
                        setLogicalDisk(logicalDisk,updatedLogicalDisk);
                        logger.info("Saved LogicalDisk with Asset Id->{}",id);
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
                    logger.info("Saved Monitor with Asset Id->{}",id);
                    customRepository.save(logicalDisk);
                }
            }
            }
        else{
            for(String[] LogicalDisks: parseResults){
                LogicalDisk logicalDisk =new LogicalDisk();
                logicalDisk.setRefId(id);
                setLogicalDisk(logicalDisk,LogicalDisks);
                logger.info("Saved Monitor with Asset Id->{}",id);
                customRepository.save(logicalDisk);
            }
        }
    }
    @Override
    public void deleteById(Long id){

        logger.info("Deleting LogicalDisk with id->{}",id);

        customRepository.deleteById(LogicalDisk.class,id,"id");

    }
    @Override
    public void update(LogicalDiskRest logicalDiskRest){

        LogicalDisk logicalDisk = new LogicalDisk();

        LogicalDiskOps logicalDiskOps = new LogicalDiskOps(logicalDisk,logicalDiskRest);

        customRepository.update(logicalDiskOps.restToEntity());

        logger.info("logicalDisk Updated with Asset Id ->{}",logicalDisk.getRefId());

    }

    @Override
    public List<LogicalDiskRest> getAllLogicalDisks(Long refId){

        List<LogicalDisk> logicalDisks = customRepository.findAllByColumnName(LogicalDisk.class,"refId",refId);

        if(logicalDisks.isEmpty()){

            throw new ResourceNotFoundException("LogicalDisk","refID",String.valueOf(refId));

        }else {

           return logicalDisks.stream().map(x->new LogicalDiskOps(x,new LogicalDiskRest()).entityToRest()).collect(Collectors.toList());

        }

    }
    private void setLogicalDisk(LogicalDisk logicalDisk,String[] data){

        logicalDisk.setSerialNumber(data[1]);

        logicalDisk.setFileSystemType(data[2]);

        logicalDisk.setSize(data[3]);
    }
    private void setcommands() {

        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        //command for fetching number of logical disks it have
        commands.put("sudo lshw -c volume | grep serial | wc -l", new String[]{});
        //commnad for getting serial number
        commands.put("sudo lshw -c volume | grep serial", new String[]{});
        //commnad for getting configuration that is FileSystemType
        commands.put("sudo lshw -c volume | grep configuration", new String[]{});
        //command for getting disks partition size
        commands.put("sudo lshw -c volume | grep size", new String[]{});

        LinuxCommandExecutorManager.add(LogicalDisk.class, commands);

    }
    private String[][] parseResults(){
        Map<String, String[]> commandResults = LinuxCommandExecutorManager.get(LogicalDisk.class);

        String[] numberOfLogicalDisks = commandResults.get("sudo lshw -c volume | grep serial | wc -l");

        if(numberOfLogicalDisks.length==0){

            return new String[][]{};

        }else{

            Pattern pattern = Pattern.compile("(?<=\\s|^)\\d+(?=\\s|$)");
            int numberOfLogicalDisk = 0;
            for(String numOfDesc: numberOfLogicalDisks) {

                if (!numOfDesc.trim().isEmpty()) {

                    Matcher matcher = pattern.matcher(numOfDesc);

                    if (matcher.find()) {

                        String number = matcher.group();
                        numberOfLogicalDisk = Integer.parseInt(number);
                        break;

                    }
                }
            }
            String[][] parsedResult = new String[numberOfLogicalDisk][commandResults.size()];
            int j=0;
            int count =1;

            for(Map.Entry<String,String[]> commandResult : commandResults.entrySet()){
                if(j==0){
                    j++;
                    continue;
                }
                String[] result = commandResult.getValue();
                if(numberOfLogicalDisk<result.length){
                    String[] validResults = new String[result.length];
                    int validResult=0;
                    for(int i=0;i<result.length;i++){
                        if(!result[i].trim().isEmpty()){
                            validResults[validResult++] = result[i];
                        }
                    }
                    result = validResults;
                }
                for(int i=0;i<numberOfLogicalDisk;i++){

                    String results = result[i];
                    if(results.contains("serial")){
                        parsedResult[i][j] = results.substring(results.indexOf("serial:") + "serial:".length());
                        continue;
                    }
                    if(results.contains("filesystem")){
                        String sub = results.substring(results.indexOf("filesystem"),results.length());
                        if(sub.split(" ").length==1) {
                            parsedResult[i][j] = sub.substring("filesystem=".length(),sub.length());
                        }else{
                            parsedResult[i][j] = sub.substring("filesystem=".length(),sub.indexOf(" "));
                        }
                        continue;
                    }else if(results.contains("mount.fstype")){
                        String sub = results.substring(results.indexOf("mount.fstype"),results.length());
                        if(sub.split("").length==1) {
                            parsedResult[i][j] = sub.substring("mount.fstype=".length(), sub.length());
                        }else{
                            parsedResult[i][j] = sub.substring("mount.fstype=".length(),sub.indexOf(" "));
                        }

                        continue;
                    }
                    if(results.contains("size")){
                        parsedResult[i][j] = results.substring(results.indexOf("size:")+"size:".length());
                    }
                }
                j++;
            }
            return parsedResult;
        }
    }

}
