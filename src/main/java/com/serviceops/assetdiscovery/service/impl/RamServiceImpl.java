package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Ram;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.RamRest;
import com.serviceops.assetdiscovery.service.interfaces.RamService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.RamOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RamServiceImpl implements RamService {
    private final CustomRepository customRepository;
    private final Logger logger = LoggerFactory.getLogger(RamServiceImpl.class);


    public RamServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }

    @Override
    public void save(Long id) {
        String[][] strings = parseResults();
        List<Ram> rams = customRepository.findAllByColumnName(Ram.class, "refId", id);
        if(!rams.isEmpty()) {
            for (String[] string: strings) {
                if (strings[0][0].equals(String.valueOf(rams.size()))) {
                    for (Ram ram : rams) {
                        ram.setRefId(id);
                        ram.setSize(string[1]);
                        ram.setWidth(string[2]);
                        ram.setSerialNumber(string[3]);
                        ram.setBankLocater(string[4]);
                        ram.setClockSpeed(string[5]);
                        ram.setManufacturer(string[6]);
                        ram.setMemoryType(string[7]);
                        customRepository.save(ram);
                        logger.debug("Updated ram with Asset Id -> {}" ,id);
                    }
                } else {
                    for (Ram ram : rams) {
                        customRepository.deleteById(Ram.class, ram.getId(), "id");
                        logger.debug("deleted ram with Asset Id -> {}" , id);
                    }
                    Ram ram = new Ram();
                    ram.setRefId(id);
                    ram.setSize(string[1]);
                    ram.setWidth(string[2]);
                    ram.setSerialNumber(string[3]);
                    ram.setBankLocater(string[4]);
                    ram.setClockSpeed(string[5]);
                    ram.setManufacturer(string[6]);
                    ram.setMemoryType(string[7]);
                    customRepository.save(ram);
                    logger.debug("saved ram with Asset Id -> {}" ,id);
                }
            }
        } else {
            for (String[] string : strings) {
                Ram ram = new Ram();
                ram.setRefId(id);
                ram.setSize(string[1]);
                ram.setWidth(string[2]);
                ram.setSerialNumber(string[3]);
                ram.setBankLocater(string[4]);
                ram.setClockSpeed(string[5]);
                ram.setManufacturer(string[6]);
                ram.setMemoryType(string[7]);
                customRepository.save(ram);
                logger.debug("saved ram with Asset Id-> {}" ,id);
            }
        }
    }
    @Override
    public RamRest findByRefId(Long refId) {

        Optional<Ram> ramOptional = customRepository.findByColumn("refId",refId,Ram.class);

        if(ramOptional.isPresent()){
            RamRest ramRest = new RamRest();
            RamOps ramOps= new RamOps(ramOptional.get(),ramRest);
            logger.info("Ram fetched with Asset Id ->{}",refId);
            return ramOps.entityToRest();
        }
        else{
            throw new ResourceNotFoundException("RamRest","refId",Long.toString(refId));
        }

    }
    @Override
    public List<RamRest> findAllByRefId(Long refId) {
        List<Ram> fetchRams = customRepository.findAllByColumnName(Ram.class, "refId", refId);
        if(fetchRams.isEmpty()){
            throw new ResourceNotFoundException("RamRest","refId",Long.toString(refId));
        }
        return fetchRams.stream().map(e->new RamOps(e,new RamRest()).entityToRest()).toList();
    }

    @Override
    public void deleteById(Long id) {
        customRepository.deleteById(Ram.class,id,"id");
        logger.info("Ram deleted of  Id ->{}",id);
    }

    @Override
    public void update(RamRest ramRest) {
        Ram ram = new Ram();
        RamOps ramOps = new RamOps(ram,ramRest);
        customRepository.save(ramOps.restToEntity());
        logger.info("Ram Updated with Asset Id ->{}",ram.getRefId());
    }

    private void setCommands() {
        String RAM_COUNT = "head -n $(sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Size: [1-9]/ {count++} END{print count}') | head -n $(sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Size: [1-9]/ {count++} END{print count}')";

        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        commands.put("sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Size: [1-9]/{print $2}' | wc -l", new String[]{});
        commands.put("sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Size: [1-9]/{print $2}'", new String[]{});
        commands.put("sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Data Width: [1-9]/{print $3}' | "+ RAM_COUNT, new String[]{});
        commands.put("sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Serial Number: /{for(i=3;i<=NF;i++) printf \"%s \", $i; printf \"\\n\"}' | "+RAM_COUNT, new String[]{});
        commands.put("sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Bank Locator: /{for(i=3;i<=NF;i++) printf \"%s \", $i; printf \"\\n\"}' |  "+RAM_COUNT, new String[]{});
        commands.put("sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Configured Clock|Memory Speed: /{for(i=4;i<=NF;i++)  printf \"%s \", $i; printf \"\\n\"}'  | "+ RAM_COUNT, new String[]{});
        commands.put("sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Manufacturer: /{for(i=2;i<=NF;i++) printf \"%s \", $i; printf \"\\n\"}' | "+RAM_COUNT, new String[]{});
        commands.put("sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Type: /{for(i=2;i<=NF;i++) printf \"%s \", $i; printf \"\\n\"}' |"+RAM_COUNT, new String[]{});
        LinuxCommandExecutorManager.add(Ram.class, commands);
    }

    private String[][] parseResults() {
        Map<String, String[]> commandResults = LinuxCommandExecutorManager.get(Ram.class);
        String[] strings = commandResults.get("sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Size: [1-9]/{print $2}' | wc -l");
        String pattern = "-?\\d+";
        Pattern intPattern = Pattern.compile(pattern);
        // TODO in dipendra ip address output wrong
        // Samsung Samsung

        for (String element : strings) {

            Matcher matcher = intPattern.matcher(element);
            if (matcher.matches()) {
                strings[0] = element.replaceAll("\\s","");
            }
        }
        String[][] parsedResult = new String[Integer.parseInt(strings[0].trim())][commandResults.size()];
        int j=0;
        for (Map.Entry<String, String[]> commandResult : commandResults.entrySet()) {
            String[] result = commandResult.getValue();
            if(j==0){
                parsedResult[0][j] = String.valueOf(strings[0].charAt(0)).trim();
                j++;
                continue;
            }
            for(int i=0;i<result.length;i++){
                parsedResult[i][j]=result[i].trim();
            }
            j++;
        }
        return parsedResult;
    }

}