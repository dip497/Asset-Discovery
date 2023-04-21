package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Bios;
import com.serviceops.assetdiscovery.exception.ComponentNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.rest.BiosRest;
import com.serviceops.assetdiscovery.service.interfaces.BiosService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.BiosOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BiosServiceImpl implements BiosService {

    private static final Logger logger = LoggerFactory.getLogger(BiosServiceImpl.class);
    CustomRepository customRepository;

    public BiosServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }

    // Saving Bios in DB or Updating the details during Re-scan
    @Override
    public void save(AssetRest assetRest) {

        List<String> parseResult = getParseResult();
        Optional<Bios> optionalBios = customRepository.findByColumn("refId", assetRest.getId(), Bios.class);

        // If optionalBios is present then do not add asset refId and updateByRefId the bios
        if (optionalBios.isPresent()) {
            Bios bios = optionalBios.get();
            setContent(assetRest, parseResult, bios);
            logger.info("Updated bios with Asset Id ->{}", assetRest.getId());
        }

        // If optionalBios is not present then set asset refId and save as new bios
        else {
            Bios bios = new Bios();
            bios.setRefId(assetRest.getId());
            setContent(assetRest, parseResult, bios);
            logger.info("Saved bios with Asset Id ->{}", assetRest.getId());
        }

    }

    // Setting the content for Bios entity
    private void setContent(AssetRest assetRest, List<String> parseResult, Bios bios) {
        try {
            bios.setSerialNumber(assetRest.getSerialNumber());
            bios.setReleaseDate(Long.parseLong(parseResult.get(0)));
            bios.setVersion(parseResult.get(1));
            bios.setSmBiosVersion(extractSMVersion(parseResult.get(2)));
            bios.setManufacturer(parseResult.get(3));
            customRepository.save(bios);
        } catch (ArrayIndexOutOfBoundsException e) {
            customRepository.save(bios);
        }
    }

    // Finding Bios by Ref ID
    @Override
    public List<BiosRest> findByRefId(long refId) {

        Optional<Bios> optionalBios = customRepository.findByColumn("refId", refId, Bios.class);

        // If optionalBios is present then return the Bios Rest
        if (optionalBios.isPresent()) {
            BiosRest biosRest = new BiosRest();
            BiosOps biosOps = new BiosOps(optionalBios.get(), biosRest);
            List<BiosRest> biosRests = new ArrayList<>();
            biosRests.add(biosOps.entityToRest());
            logger.info("Bios fetched with Asset Id ->{}", refId);
            return biosRests;
        }

        // If optionalBios is not then throw ComponentNotFoundException
        else {
            return new ArrayList<>();
        }

    }

    // Deleting Asset by Ref ID
    @Override
    public boolean deleteByRefId(long refId) {

        // Deleting the Bios at given refId
        boolean isDeleted = customRepository.deleteById(Bios.class, refId, "refId");

        if (isDeleted) {
            logger.info("Bios deleted with Asset Id ->{}", refId);
        } else {
            logger.info("Bios could not be deleted with Asset Id ->{}", refId);
        }

        return isDeleted;
    }

    // Updating Asset by ID
    public BiosRest updateByRefId(long refId, BiosRest biosRest) {

        // If Bios is present then move further to updateByRefId the Bios or else throw ComponentNotFoundException
        Optional<Bios> optionalBios = customRepository.findByColumn("refId", refId, Bios.class);

        // If Bios is present then updateByRefId data as per BiosRest
        if (optionalBios.isPresent()) {
            Bios bios = optionalBios.get();
            BiosOps biosOps = new BiosOps(bios, biosRest);
            customRepository.save(biosOps.restToEntity());
            logger.info("Bios Updated with Asset Id ->{}", bios.getRefId());
            return biosRest;
        }

        // If Bios not present then throw ComponentNotFoundException
        else {
            logger.error("Bios not found for Asset with ID ->{}", refId);
            throw new ComponentNotFoundException("BiosRest", "refId", refId);
        }
    }

    // Setting the Commands to fetch Bios details
    private void setCommands() {

        // HashMap for setting the Multiple commands and their value in String[]
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();

        // Command for getting the bios release date.
        commands.put("date -d \"$(sudo dmidecode -s bios-release-date)\" +\"%s%3N\"", new String[] {});

        // Command for getting the bios version.
        commands.put("sudo dmidecode -s bios-version", new String[] {});

        // Command for getting the sm version.
        commands.put("sudo dmidecode -t bios | sed -n '3p'", new String[] {});

        // Command for getting the vendor.
        commands.put("sudo dmidecode -t bios | awk '/Vendor:/ {print $2}'", new String[] {});

        // Adding all the commands to the Main HasMap where the class Bios is the key for all the commands
        LinuxCommandExecutorManager.add(Bios.class, commands);
    }

    // Parsing Data for Bios
    private List<String> getParseResult() {
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(Bios.class);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String[]> result : stringMap.entrySet()) {
            String[] values = result.getValue();

            if (values.length < 4) {
                for (String ans : values) {
                    if (!ans.trim().isEmpty()) {
                        list.add(ans);
                    }
                }
            }


        }
        return list;
    }

    private String extractSMVersion(String version) {
        Pattern pattern = Pattern.compile("SMBIOS\\s+(\\d+\\.\\d+(\\.\\d+)?)\\s+present");
        Matcher matcher = pattern.matcher(version);
        String versionNumber = "";
        if (matcher.find()) {
            versionNumber = matcher.group(1);
        }
        return versionNumber;
    }



}
