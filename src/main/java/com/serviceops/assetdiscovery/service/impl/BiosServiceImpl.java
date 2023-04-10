package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Bios;
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

    @Override
    public void save(AssetRest assetRest)  {

        List<String> parseResult = getParseResult();
        Optional<Bios> fetchBios = customRepository.findByColumn("id", assetRest.getId(), Bios.class);
        if(fetchBios.isPresent()){
            Bios bios = fetchBios.get();
            bios.setRealeaseDate(parseResult.get(0));
            bios.setVersion(parseResult.get(1));
            bios.setName(parseResult.get(2));
            bios.setSmBiosVersion(parseResult.get(2));
            bios.setDescription(parseResult.get(2));
            bios.setManufacturer(parseResult.get(3));
            customRepository.save(bios);
            logger.debug("Updating bios with Asset Id ->{}",assetRest.getId());
        }
        else{
            Bios bios = new Bios();
            System.out.println("Serial number in bios :"+assetRest);
            bios.setSerialNumber(assetRest.getSerialNumber());
            bios.setRefId(assetRest.getId());
            bios.setRealeaseDate(parseResult.get(0));
            bios.setVersion(parseResult.get(1));
            bios.setName(parseResult.get(2));
            bios.setSmBiosVersion(parseResult.get(2));
            bios.setDescription(parseResult.get(2));
            bios.setManufacturer(parseResult.get(3));
            customRepository.save(bios);
            logger.debug("Saving bios with Asset Id ->{}",assetRest.getId());
        }

    }

    @Override
    public BiosRest findByRefId(Long refId) {
        Bios bios = customRepository.findByColumn("refId",refId,Bios.class).get();
        BiosRest biosRest = new BiosRest();
        BiosOps biosOps= new BiosOps(bios,biosRest);
        return biosOps.entityToRest();
    }

    private void setCommands(){
        LinkedHashMap<String,String[]> commands = new LinkedHashMap<>();
        commands.put("sudo dmidecode -s bios-release-date",new String[]{});
        commands.put("sudo dmidecode -s bios-version",new String[]{});
        commands.put("sudo dmidecode -t bios | sed -n '3p'",new String[]{});
        commands.put("sudo dmidecode -t bios | awk '/Vendor:/ {print $2}'",new String[]{});
        LinuxCommandExecutorManager.add(Bios.class,commands);
    }

    private List<String> getParseResult(){
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(Bios.class);
        List<String> list= new ArrayList<>();
        for (Map.Entry<String ,String[]> result: stringMap.entrySet()) {
            String[] values = result.getValue();
            for (int i=0;i<values.length;++i) {
                if(values[i].contains("admin"))
                    i++;
                else {
                    list.add(values[i]);
                }
            }
        }
        return list;
    }

}
