package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Keyboard;
import com.serviceops.assetdiscovery.exception.ComponentNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.KeyboardRest;
import com.serviceops.assetdiscovery.service.interfaces.KeyboardService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.KeyboardOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class KeyboardServiceImpl implements KeyboardService {
    private static final Logger logger = LoggerFactory.getLogger(KeyboardServiceImpl.class);
    private final CustomRepository customRepository;
    private final KeyboardOps keyboardOps;

    public KeyboardServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        keyboardOps = new KeyboardOps();
        setCommands();
    }

    @Override
    public void save(long id) {
        String[][] strings = parseResults();
        List<Keyboard> keyboards = customRepository.findAllByColumn( "refId", id,Keyboard.class);
        if (!keyboards.isEmpty()) {
            if (strings[0][0].equals(String.valueOf(keyboards.size()))) {
                for (int i = 0; i < keyboards.size(); i++) {
                    keyboards.get(i).setRefId(id);
                    setKeyboard(keyboards.get(i), strings[i]);
                    customRepository.save(keyboards.get(i));
                    logger.debug("Updated keyboard with Asset Id -> {}", id);
                }
            } else {
                for (Keyboard keyboard : keyboards) {
                    customRepository.deleteById(Keyboard.class, keyboard.getId(), "id");
                    logger.debug("deleted keyboard with Asset Id -> {}", id);
                }
                for (String[] string : strings) {
                    Keyboard ram = new Keyboard();
                    ram.setRefId(id);
                    setKeyboard(ram, string);
                    customRepository.save(ram);
                    logger.debug("updating keyboard with Asset Id -> {}", id);
                }
            }
        } else {
            for (String[] string : strings) {
                Keyboard ram = new Keyboard();
                ram.setRefId(id);
                setKeyboard(ram, string);
                customRepository.save(ram);
                logger.debug("saved keyboard with Asset Id-> {}", id);
            }
        }
    }

    @Override
    public List<KeyboardRest> findAllByRefId(long refId) {
        List<Keyboard> fetchKeyboard = customRepository.findAllByColumn("refId", refId,Keyboard.class);
        if (fetchKeyboard.isEmpty()) {
            logger.error("Keyboard not found with refId -> {} ", refId);
            return List.of();
        }
        logger.info("fetched list of keyboard for refId ->{}", refId);
        return fetchKeyboard.stream().map(e -> keyboardOps.entityToRest(e, new KeyboardRest()))
                .toList();
    }

    @Override
    public KeyboardRest updateById(long refId, long id, KeyboardRest keyboardRest) {
        HashMap<String, Long> fields = new HashMap<>();
        fields.put("refId", refId);
        fields.put("id", id);
        List<Keyboard> keyboardList = customRepository.findByColumns(fields, Keyboard.class);
        if (keyboardList.isEmpty()) {
            logger.error("Keyboard not found with Asset Id -> {} ", refId);
            throw new ComponentNotFoundException("Keyboard", "refId", refId);
        } else {
            Keyboard keyboard = keyboardList.get(0);
            customRepository.save(keyboardOps.restToEntity(keyboard, keyboardRest));
            logger.info("Keyboard Updated with Asset Id ->{}", keyboard.getRefId());
            return keyboardOps.entityToRest(keyboard, keyboardRest);
        }
    }

    @Override
    public boolean deleteById(long refId, long id) {
        boolean isDeleted = customRepository.deleteById(Keyboard.class, id, "id");
        if (isDeleted) {
            logger.info("MotherBoard deleted with Asset Id ->{}", refId);
        } else {
            logger.info("MotherBoard not deleted with Asset Id ->{}", refId);
        }
        return isDeleted;
    }

    private void setKeyboard(Keyboard keyboard, String[] data) {
        keyboard.setName(data[1]);
    }


    private void setCommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        commands.put("cat /proc/bus/input/devices | awk -F= '/keyboard/ && NF==2 {print $2}' | wc -l",
                new String[] {});
        commands.put("cat /proc/bus/input/devices | awk -F= '/keyboard/ && NF==2 {print $2}'",
                new String[] {});
        LinuxCommandExecutorManager.add(Keyboard.class, commands);
    }

    private String[][] parseResults() {
        Map<String, String[]> commandResults = LinuxCommandExecutorManager.get(Keyboard.class);
        String[] strings = commandResults.get(
                "cat /proc/bus/input/devices | awk -F= '/keyboard/ && NF==2 {print $2}' | wc -l");
        String pattern = "-?\\d+";
        Pattern intPattern = Pattern.compile(pattern);

        for (String element : strings) {

            Matcher matcher = intPattern.matcher(element);
            if (matcher.matches()) {
                strings[0] = element.replaceAll("\\s", "");
            }
        }
        int keyboarCount = Integer.parseInt(strings[0]);
        String[][] parsedResult = new String[Integer.parseInt(strings[0].trim())][commandResults.size()];
        int j = 0;
        for (Map.Entry<String, String[]> commandResult : commandResults.entrySet()) {
            String[] result = commandResult.getValue();
            if (keyboarCount < result.length) {
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
            for (int i = 0; i < keyboarCount; i++) {
                parsedResult[i][j] = result[i].replaceAll("\"", "");
            }
            j++;
        }
        return parsedResult;
    }
}
