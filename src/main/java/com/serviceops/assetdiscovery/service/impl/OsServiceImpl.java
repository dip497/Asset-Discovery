package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.OS;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.OSRest;
import com.serviceops.assetdiscovery.service.interfaces.OsService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.OsOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OsServiceImpl implements OsService {

    CustomRepository customRepository;

    Logger logger = LoggerFactory.getLogger(OsServiceImpl.class);

    public OsServiceImpl(CustomRepository customRepository){
        this.customRepository =customRepository;
        setCommands();
    }

    // Saving Bios in DB or Updating the details during Re-scan
    @Override
    public void save(Long refId) {

        List<String> parseResult = getParseResult();
        Optional<OS> fetchOS = customRepository.findByColumn("refId",refId,OS.class);
        OS os = new OS();
        if(fetchOS.isPresent()){
            os.setOsName(parseResult.get(0));
            os.setOsArchitecture(parseResult.get(1));
            os.setOsVersion(parseResult.get(0));
            customRepository.save(os);
            logger.info("Updated os with Asset Id ->{}",refId);
        }
        else{
            os.setRefId(refId);
            os.setOsName(parseResult.get(0));
            os.setOsArchitecture(parseResult.get(1));
            os.setOsVersion(parseResult.get(0));
            customRepository.save(os);
            logger.info("Saved bios with Asset Id ->{}",refId);
        }
    }

    // Finding the OS by Ref ID
    @Override
    public OSRest findByRefId(Long refId) {

        Optional<OS> optionalOS = customRepository.findByColumn("refId",refId,OS.class);

        if(optionalOS.isPresent()){
            OSRest osRest = new OSRest();
            OsOps osOps= new OsOps(optionalOS.get(),osRest);
            logger.info("OS fetched with Asset Id ->{}",refId);
            return osOps.entityToRest();
        }
        else{
            logger.error("OS not found for Asset with ID ->{}", refId);
            throw new ResourceNotFoundException("OSRest","refId",Long.toString(refId));
        }

    }

    // Deleting the OS by Ref ID
    @Override
    public void deleteByRefId(Long refId) {
        customRepository.deleteById(OS.class,refId,"refId");
        logger.info("OS deleted with Asset Id ->{}",refId);
    }

    // Updating the data for OS
    @Override
    public void update(OSRest osRest) {
        OS os = new OS();
        OsOps osOps = new OsOps(os,osRest);
        customRepository.update(osOps.restToEntity());
        logger.info("OS Updated with Asset Id ->{}",osRest.getRefId());
    }

    // Setting the Commands to fetch OS details
    private void setCommands(){

        // HashMap for setting the Multiple commands and their value in String[]
        LinkedHashMap<String,String[]> commands = new LinkedHashMap<>();

        // Find the OS name
        commands.put("hostnamectl | grep \"Operating System:\" | awk '{$1=$2=\"\"; print $0}'",new String[]{});

        // Find the OS architecture
        commands.put("uname -m",new String[]{});

        LinuxCommandExecutorManager.add(OS.class,commands);
    }

    // Parsing Data for OS
    private List<String> getParseResult(){
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(OS.class);
        List<String> list= new ArrayList<>();
        for (Map.Entry<String ,String[]> result: stringMap.entrySet()) {
            String[] values = result.getValue();
            for (String value : values) {
                if (value == null)
                    continue;
                list.add(value);

            }
        }
        return list;
    }

}
