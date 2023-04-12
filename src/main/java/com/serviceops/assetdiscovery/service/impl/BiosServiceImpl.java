package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Bios;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.rest.BiosRest;
import com.serviceops.assetdiscovery.service.interfaces.BiosService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.BiosOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BiosServiceImpl implements BiosService {

    CustomRepository customRepository;

    Logger logger = LoggerFactory.getLogger(AssetServiceImpl.class);

    public BiosServiceImpl(CustomRepository customRepository){
        this.customRepository = customRepository;
        setCommands();
    }

    // Saving Bios in DB or Updating the details during Re-scan
    @Override
    public void save(AssetRest assetRest)  {

        List<String> parseResult = getParseResult();
        Optional<Bios> fetchBios = customRepository.findByColumn("refId", assetRest.getId(), Bios.class);
        if(fetchBios.isPresent()){
            Bios bios = fetchBios.get();
            bios.setReleaseDate(parseResult.get(0));
            bios.setVersion(parseResult.get(1));
            bios.setName(parseResult.get(2));
            bios.setSmBiosVersion(parseResult.get(2));
            bios.setDescription(parseResult.get(2));
            bios.setManufacturer(parseResult.get(3));
            customRepository.save(bios);
            logger.info("Updated bios with Asset Id ->{}",assetRest.getId());
        }
        else{
            Bios bios = new Bios();
            bios.setSerialNumber(assetRest.getSerialNumber());
            bios.setRefId(assetRest.getId());
            bios.setReleaseDate(parseResult.get(0));
            bios.setVersion(parseResult.get(1));
            bios.setName(parseResult.get(2));
            bios.setSmBiosVersion(parseResult.get(2));
            bios.setDescription(parseResult.get(2));
            bios.setManufacturer(parseResult.get(3));
            customRepository.save(bios);
            logger.info("Saved bios with Asset Id ->{}",assetRest.getId());
        }

    }

    // Finding Bios by Ref ID
    @Override
    public BiosRest findByRefId(Long refId) {

        Optional<Bios> biosOptional = customRepository.findByColumn("refId",refId,Bios.class);

        if(biosOptional.isPresent()){
            BiosRest biosRest = new BiosRest();
            BiosOps biosOps= new BiosOps(biosOptional.get(),biosRest);
            logger.info("Bios fetched with Asset Id ->{}",refId);
            return biosOps.entityToRest();
        }
        else{
            logger.error("Bios not found for Asset with ID ->{}", refId);
            throw new ResourceNotFoundException("BiosRest","refId",Long.toString(refId));
        }

    }

    // Deleting Asset by Ref ID
    @Override
    public void deleteByRefId(Long refId) {
        customRepository.deleteById(Bios.class,refId,"refId");
        logger.info("Bios deleted with Asset Id ->{}",refId);
    }

    // Updating Asset by ID
    @Override
    public void update(BiosRest biosRest) {
        Bios bios = new Bios();
        BiosOps biosOps= new BiosOps(bios,biosRest);
        customRepository.update(biosOps.restToEntity());
        logger.info("Bios Updated with Asset Id ->{}",bios.getRefId());
    }

    // Setting the Commands to fetch Bios details
    private void setCommands(){

        // HashMap for setting the Multiple commands and their value in String[]
        LinkedHashMap<String,String[]> commands = new LinkedHashMap<>();

        // Command for getting the bios release date.
        commands.put("sudo dmidecode -s bios-release-date",new String[]{});

        // Command for getting the bios version.
        commands.put("sudo dmidecode -s bios-version",new String[]{});

        // Command for getting the sm version.
        commands.put("sudo dmidecode -t bios | sed -n '3p'",new String[]{});

        // Command for getting the vendor.
        commands.put("sudo dmidecode -t bios | awk '/Vendor:/ {print $2}'",new String[]{});

        // Adding all the commands to the Main HasMap where the class Bios is the key for all the commands
        LinuxCommandExecutorManager.add(Bios.class,commands);
    }

    // Parsing Data for Bios
    private List<String> getParseResult(){
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(Bios.class);
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
