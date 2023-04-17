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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Optional<Bios> optionalBios = customRepository.findByColumn("refId", assetRest.getId(), Bios.class);

        // If optionalBios is present then do not add asset refId and update the bios
        if(optionalBios.isPresent()){
            Bios bios = optionalBios.get();
            setContent(assetRest, parseResult, bios);
            logger.info("Updated bios with Asset Id ->{}",assetRest.getId());
        }

        // If optionalBios is not present then set asset refId and save as new bios
        else{
            Bios bios = new Bios();
            bios.setRefId(assetRest.getId());
            setContent(assetRest, parseResult, bios);
            logger.info("Saved bios with Asset Id ->{}",assetRest.getId());
        }

    }

    // Setting the Bios Content for save method
    private void setContent(AssetRest assetRest, List<String> parseResult, Bios bios) {
        bios.setSerialNumber(assetRest.getSerialNumber());
        bios.setReleaseDate(parseResult.get(0));
        bios.setVersion(parseResult.get(1));
        bios.setName(parseResult.get(2));
        bios.setSmBiosVersion(extractSMVersion(parseResult.get(2)));
        bios.setDescription(parseResult.get(2));
        bios.setManufacturer(parseResult.get(3));
        customRepository.save(bios);
    }

    // Finding Bios by Ref ID
    @Override
    public List<BiosRest> findByRefId(Long refId) {

        Optional<Bios> optionalBios = customRepository.findByColumn("refId",refId,Bios.class);

        // If optionalBios is present then return the Bios Rest
        if(optionalBios.isPresent()){
            BiosRest biosRest = new BiosRest();
            BiosOps biosOps= new BiosOps(optionalBios.get(),biosRest);
            List<BiosRest> biosRests = new ArrayList<>();
            biosRests.add(biosOps.entityToRest());
            logger.info("Bios fetched with Asset Id ->{}",refId);
            return biosRests;
        }

        // If optionalBios is not then throw ResourceNotFoundException
        else{
           return new ArrayList<>();
        }

    }

    // Deleting Asset by Ref ID
    @Override
    public void deleteByRefId(Long refId) {

        // If Bios is present then move further to delete the Bios or else throw ResourceNotFoundException
        findByRefId(refId);

        // Deleting the Bios at given refId
        customRepository.deleteById(Bios.class,refId,"refId");

        logger.info("Bios deleted with Asset Id ->{}",refId);
    }

    // Updating Asset by ID
    public void update(Long refId, BiosRest biosRest) {

        // If Bios is present then move further to update the Bios or else throw ResourceNotFoundException
        Optional<Bios> optionalBios = customRepository.findByColumn("refId",refId,Bios.class);

        // If Bios is present then update data as per BiosRest
        if(optionalBios.isPresent()){
            Bios bios = optionalBios.get();
            BiosOps biosOps= new BiosOps(bios,biosRest);
            customRepository.save(biosOps.restToEntity());
            logger.info("Bios Updated with Asset Id ->{}",bios.getRefId());
        }

        // If Bios not present then throw ResourceNotFoundException
        else {
            logger.error("Bios not found for Asset with ID ->{}", refId);
            throw new ResourceNotFoundException("BiosRest","refId",Long.toString(refId));
        }
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
                if (value.trim().isEmpty())
                    continue;


                System.out.println(value);

                list.add(value);

            }
        }
        return list;
    }

    private String extractSMVersion(String version){
        Pattern pattern = Pattern.compile("SMBIOS\\s+(\\d+\\.\\d+(\\.\\d+)?)\\s+present");
        Matcher matcher = pattern.matcher(version);
        String versionNumber ="";
        if (matcher.find()) {
            versionNumber = matcher.group(1);
        }
        return versionNumber;
    }

}
