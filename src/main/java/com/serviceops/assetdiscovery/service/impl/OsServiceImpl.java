package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.OS;
import com.serviceops.assetdiscovery.entity.enums.Architecture;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.OSRest;
import com.serviceops.assetdiscovery.service.interfaces.OsService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.OsOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OsServiceImpl implements OsService {

    CustomRepository customRepository;

    private static final Logger logger = LoggerFactory.getLogger(OsServiceImpl.class);

    public OsServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }

    // Helps in Parsing the String date to Milliseconds
    private static long parseDate(String datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");
        LocalDateTime dateTime = LocalDateTime.parse(datetime, formatter);
        return dateTime.toInstant(java.time.ZoneOffset.UTC).toEpochMilli();
    }

    // Saving Bios in DB or Updating the details during Re-scan
    @Override
    public void save(long refId) {

        List<String> parseResult = getParseResult();
        Optional<OS> optionalOS = customRepository.findByColumn("refId", refId, OS.class);

        // If optionalOs is present then do not add asset refId and updateByRefId the os
        if (optionalOS.isPresent()) {
            OS os = optionalOS.get();
            os.setOsName(parseResult.get(0));
            os.setArchitecture(
                    parseResult.get(2).contains("64") ? Architecture.SIXTY_FOUR : Architecture.THIRTY_TWO);
            os.setOsVersion(parseResult.get(1));
            os.setInstalledDate(parseDate(parseResult.get(3)));
            customRepository.save(os);
            logger.info("Updated os with Asset Id ->{}", refId);
        }

        // If optionalOs is not present then set asset refId and save as new os
        else {
            OS os = new OS();
            os.setRefId(refId);
            os.setOsName(parseResult.get(0));
            os.setArchitecture(
                    parseResult.get(2).contains("64") ? Architecture.SIXTY_FOUR : Architecture.THIRTY_TWO);
            os.setOsVersion(parseResult.get(1));
            os.setInstalledDate(parseDate(parseResult.get(3)));
            customRepository.save(os);
            logger.info("Saved bios with Asset Id ->{}", refId);
        }
    }

    // Finding the OS by Ref ID
    @Override
    public List<OSRest> findByRefId(long refId) {

        Optional<OS> optionalOS = customRepository.findByColumn("refId", refId, OS.class);

        // If optionalOS is present then return the os Rest
        if (optionalOS.isPresent()) {
            OSRest osRest = new OSRest();
            OsOps osOps = new OsOps(optionalOS.get(), osRest);
            List<OSRest> osRests = new ArrayList<>();
            osRests.add(osOps.entityToRest());
            logger.info("OS fetched with Asset Id ->{}", refId);
            return osRests;
        }

        // If optionalOS is not present then throw ComponentNotFoundException
        else {
            logger.error("OS not found by IP ->{}", refId);
            return new ArrayList<>();
        }

    }

    // Deleting the OS by Ref ID
    @Override
    public void deleteByRefId(long refId) {

        // Deleting the Os at given refId
        customRepository.deleteById(OS.class, refId, "refId");

        logger.info("OS deleted with Asset Id ->{}", refId);
    }

    // Updating the data for OS
    @Override
    public void update(long refId, OSRest osRest) {

        Optional<OS> optionalOS = customRepository.findByColumn("refId", refId, OS.class);

        // If OS is present then updateByRefId data as per OsRest
        if (optionalOS.isPresent()) {
            OS os = optionalOS.get();
            OsOps osOps = new OsOps(os, osRest);
            customRepository.save(osOps.restToEntity());
            logger.info("OS Updated with Asset Id ->{}", osRest.getRefId());
        }

        // If OS is not present then throw ComponentNotFoundException
        else {
            logger.error("OS not found for Asset with ID ->{}", refId);
        }

    }

    // Setting the Commands to fetch OS details
    private void setCommands() {

        // HashMap for setting the Multiple commands and their value in String[]
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();

        // Find the OS name
        commands.put("grep '^NAME=' /etc/os-release | cut -d'\"' -f2", new String[] {});

        // Find the OS version
        commands.put("grep '^VERSION=' /etc/os-release | cut -d'\"' -f2", new String[] {});

        // Find the OS architecture
        commands.put("uname -m", new String[] {});

        // Find the installed date of OS.
        commands.put("ls -lact --full-time /etc | tail -1 | awk '{print $6,$7}'", new String[] {});

        LinuxCommandExecutorManager.add(OS.class, commands);
    }

    // Parsing Data for OS
    private List<String> getParseResult() {
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(OS.class);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String[]> result : stringMap.entrySet()) {
            String[] values = result.getValue();
            for (String value : values) {
                if (value == null)
                    continue;

                if (value.equals("No LSB modules are available."))
                    continue;

                list.add(value);

            }
        }
        return list;
    }


}


