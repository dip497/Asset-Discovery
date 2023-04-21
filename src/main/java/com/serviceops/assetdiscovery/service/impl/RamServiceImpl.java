package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Ram;
import com.serviceops.assetdiscovery.exception.ComponentNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.RamRest;
import com.serviceops.assetdiscovery.service.interfaces.RamService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.UnitConverter;
import com.serviceops.assetdiscovery.utils.mapper.RamOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RamServiceImpl implements RamService {
    private static final Logger logger = LoggerFactory.getLogger(RamServiceImpl.class);
    private final CustomRepository customRepository;

    public RamServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }

    @Override
    public void save(long id) {
        String[][] strings = parseResults();
        List<Ram> rams = customRepository.findAllByColumnName(Ram.class, "refId", id);
        if (!rams.isEmpty()) {
            if (strings[0][0].equals(String.valueOf(rams.size()))) {
                for (int i = 0; i < rams.size(); i++) {
                    rams.get(i).setRefId(id);
                    setRam(rams.get(i), strings[i]);
                    customRepository.save(rams.get(i));
                    logger.debug("Updated ram with Asset Id -> {}", id);
                }
            } else {
                for (Ram ram : rams) {
                    customRepository.deleteById(Ram.class, ram.getId(), "id");
                    logger.debug("deleted ram with Asset Id -> {}", id);
                }
                for (String[] string : strings) {
                    Ram ram = new Ram();
                    ram.setRefId(id);
                    setRam(ram, string);
                    customRepository.save(ram);
                    logger.debug("updating ram with Asset Id -> {}", id);
                }
            }
        } else {
            for (String[] string : strings) {
                Ram ram = new Ram();
                ram.setRefId(id);
                setRam(ram, string);
                customRepository.save(ram);
                logger.debug("saved ram with Asset Id-> {}", id);
            }
        }
    }

    @Override
    public RamRest findByRefId(long refId) {
        Optional<Ram> ramOptional = customRepository.findByColumn("refId", refId, Ram.class);
        if (ramOptional.isPresent()) {
            RamRest ramRest = new RamRest();
            RamOps ramOps = new RamOps(ramOptional.get(), ramRest);
            logger.info("Ram fetched with Asset Id ->{}", refId);
            return ramOps.entityToRest();
        } else {
            throw new ComponentNotFoundException("Ram", "refId", refId);
        }

    }

    @Override
    public List<RamRest> findAllByRefId(long refId) {
        List<Ram> fetchRams = customRepository.findAllByColumnName(Ram.class, "refId", refId);
        if (fetchRams.isEmpty()) {
            logger.error("Ram not found with refId -> {} ", refId);
            return List.of();
        }
        logger.info("fetched list of ram for refId ->{}", refId);
        return fetchRams.stream().map(e -> new RamOps(e, new RamRest()).entityToRest()).toList();
    }

    @Override
    public RamRest update(long refId, long id, RamRest ramRest) {
        HashMap<String, Long> fields = new HashMap<>();
        fields.put("refId", refId);
        fields.put("id", id);
        List<Ram> ramList = customRepository.findByColumns(fields, Ram.class);
        if (ramList.isEmpty()) {
            logger.error("Ram not found with Asset Id -> {} ", refId);
            throw new ComponentNotFoundException("Ram", "refId", refId);
        } else {
            Ram ram = ramList.get(0);
            RamOps ramOps = new RamOps(ram, ramRest);
            customRepository.save(ramOps.restToEntity());
            logger.info("Ram Updated with Asset Id ->{}", ram.getRefId());
            return ramOps.entityToRest();
        }
    }

    @Override
    public boolean deleteById(long refId, long id) {
        boolean isDeleted = customRepository.deleteById(Ram.class, id, "id");
        if (isDeleted) {
            logger.info("MotherBoard deleted with Asset Id ->{}", refId);
        } else {
            logger.info("MotherBoard not deleted with Asset Id ->{}", refId);
        }
        return isDeleted;
    }

    private void setRam(Ram ram, String[] data) {
        ram.setSize(UnitConverter.convertToBytes(data[1]));
        ram.setWidth(UnitConverter.convertToBytes(data[2]));
        ram.setSerialNumber(data[3]);
        ram.setBankLocater(data[4]);
        ram.setClockSpeed(UnitConverter.convertToBytes(data[5]));
        ram.setManufacturer(data[6]);
        ram.setMemoryType(data[7]);
    }

    private void setCommands() {
        String RAM_COUNT =
                "head -n $(sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Size: [1-9]/ {count++} END{print count}') | head -n $(sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Size: [1-9]/ {count++} END{print count}')";

        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        commands.put(
                "sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Size: [1-9]/{print $2}' | wc -l",
                new String[] {});
        commands.put(
                "sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Size: /{for(i=2;i<=NF;i++) printf \"%s \", $i; printf \"\\n\"}' |"
                        + RAM_COUNT, new String[] {});
        commands.put(
                "sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Data Width: /{for(i=3;i<=NF;i++) printf \"%s \", $i; printf \"\\n\"}' | "
                        + RAM_COUNT, new String[] {});
        commands.put(
                "sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Serial Number: /{for(i=3;i<=NF;i++) printf \"%s \", $i; printf \"\\n\"}' | "
                        + RAM_COUNT, new String[] {});
        commands.put(
                "sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Bank Locator: /{for(i=3;i<=NF;i++) printf \"%s \", $i; printf \"\\n\"}' |  "
                        + RAM_COUNT, new String[] {});
        commands.put(
                "sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Configured Clock|Memory Speed: /{for(i=4;i<=NF;i++)  printf \"%s \", $i; printf \"\\n\"}'  | "
                        + RAM_COUNT, new String[] {});
        commands.put(
                "sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Manufacturer: /{for(i=2;i<=NF;i++) printf \"%s \", $i; printf \"\\n\"}' | "
                        + RAM_COUNT, new String[] {});
        commands.put(
                "sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Type: /{for(i=2;i<=NF;i++) printf \"%s \", $i; printf \"\\n\"}' |"
                        + RAM_COUNT, new String[] {});
        LinuxCommandExecutorManager.add(Ram.class, commands);
    }

    private String[][] parseResults() {
        Map<String, String[]> commandResults = LinuxCommandExecutorManager.get(Ram.class);
        String[] strings = commandResults.get(
                "sudo dmidecode -t 17 | awk '/Memory Device/{flag=1; next} /Handle /{flag=0} flag && /Size: [1-9]/{print $2}' | wc -l");
        String pattern = "-?\\d+";
        Pattern intPattern = Pattern.compile(pattern);

        for (String element : strings) {

            Matcher matcher = intPattern.matcher(element);
            if (matcher.matches()) {
                strings[0] = element.replaceAll("\\s", "");
            }
        }
        int ramCount = Integer.parseInt(strings[0]);
        String[][] parsedResult = new String[Integer.parseInt(strings[0].trim())][commandResults.size()];
        int j = 0;
        for (Map.Entry<String, String[]> commandResult : commandResults.entrySet()) {
            String[] result = commandResult.getValue();
            if (ramCount < result.length) {
                for (int i = 1; i < result.length; i++) {
                    if (result[i - 1].trim().isEmpty()) {
                        result[i - 1] = result[i];
                    }
                }
            }
            if (j == 0) {
                parsedResult[0][j] = String.valueOf(strings[0].charAt(0)).trim();
                j++;
                continue;
            }
            for (int i = 0; i < ramCount; i++) {
                parsedResult[i][j] = result[i];
            }
            j++;
        }
        return parsedResult;
    }

}