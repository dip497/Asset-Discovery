package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.LogicalDisk;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.LogicalDiskRest;
import com.serviceops.assetdiscovery.service.interfaces.LogicalDiskService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.LogicalDiskOps;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        List<LogicalDisk> logicalDisks = customRepository.findAllByColumnName(LogicalDisk.class,"refId",id);
        if(logicalDisks.isEmpty()){
            if(logicalDisks.size()==parseResults.length){
                for(LogicalDisk logicalDisk : logicalDisks){
                    for(String[] updatedLogicalDisk : parseResults){
                        logicalDisk.setSerialNumber(updatedLogicalDisk[1]);
                        logicalDisk.setFileSystemType(updatedLogicalDisk[2]);
                        logicalDisk.setSize(updatedLogicalDisk[3]);
                        customRepository.save(logicalDisk);
                    }
                }
            }else{
                for(LogicalDisk logicalDisk: logicalDisks){
                    customRepository.deleteById(LogicalDisk.class,logicalDisk.getId(),"id");
                }
                for(String[] updatedLogicalDisk: parseResults){
                    LogicalDisk logicalDisk =new LogicalDisk();
                    logicalDisk.setRefId(id);
                    logicalDisk.setSerialNumber(updatedLogicalDisk[1]);
                    logicalDisk.setFileSystemType(updatedLogicalDisk[2]);
                    logicalDisk.setSize(updatedLogicalDisk[3]);
                    customRepository.save(logicalDisk);
                }
            }
            }
        else{
            for(String[] LogicalDisks: parseResults){
                LogicalDisk logicalDisk =new LogicalDisk();
                logicalDisk.setRefId(id);
                logicalDisk.setSerialNumber(LogicalDisks[1]);
                logicalDisk.setFileSystemType(LogicalDisks[2]);
                logicalDisk.setSize(LogicalDisks[3]);
                customRepository.save(logicalDisk);
            }
        }
    }
    @Override
    public void delete(Long id){

        logger.info("Deleting LogicalDisk with id->{}",id);

        customRepository.deleteById(LogicalDisk.class,id,"id");

    }
    @Override
    public void update(LogicalDiskRest logicalDiskRest){
        LogicalDisk logicalDisk = new LogicalDisk();
        LogicalDiskOps logicalDiskOps = new LogicalDiskOps(logicalDisk,logicalDiskRest);
        customRepository.update(logicalDiskOps.restToEntity(logicalDiskRest));
        logger.info("logicalDisk Updated with Asset Id ->{}",logicalDisk.getRefId());

    }
    private void setcommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        commands.put("sudo lshw -c volume | grep serial | wc -l", new String[]{});
        commands.put("sudo lshw -c volume | grep serial", new String[]{});
        commands.put("sudo lshw -c volume | grep configuration", new String[]{});
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
                for(int i=0;i<numberOfLogicalDisk;i++){
                    String results = result[i];
                    if(results.contains("serial")){
                        parsedResult[i][j] = results.substring(results.indexOf("serial:") + "serial:".length());
                        continue;
                    }
                    if(results.contains("filesystem")){
                        String sub = results.substring(results.indexOf("filesystem"),results.length());
                        String ans = sub.substring("filesystem=".length(),sub.indexOf(" "));
                        parsedResult[i][j]=ans;
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
