package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Keyboard;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.KeyboardRest;
import com.serviceops.assetdiscovery.service.interfaces.KeyboardService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.KeyboardOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class KeyboardServiceImpl implements KeyboardService {
    private final CustomRepository customRepository;
    private final Logger logger = LoggerFactory.getLogger(KeyboardServiceImpl.class);

    public KeyboardServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }

    @Override
    public void save(Long id) {
        Optional<Keyboard> optionalKeyboard = customRepository.findByColumn("refId", id, Keyboard.class);
        if (optionalKeyboard.isPresent()) {
            List<String> parseResult = getParseResult();
            Keyboard keyboard = optionalKeyboard.get();
            keyboard.setName(parseResult.get(0));
            customRepository.save(keyboard);
            logger.debug("Updated keyboard with refId -> {}", keyboard.getRefId());
        } else {
            Keyboard keyboard = new Keyboard();
            keyboard.setRefId(id);
            List<String> parseResult = getParseResult();
            keyboard.setManufacturer(parseResult.get(0));
            customRepository.save(keyboard);
            logger.debug("Saved keyboard with refId -> {}", keyboard.getRefId());

        }

    }
    @Override
    public KeyboardRest findByRefId(Long refId) {

        Optional<Keyboard> keyboardOptional = customRepository.findByColumn("refId",refId,Keyboard.class);

        if(keyboardOptional.isPresent()){
            KeyboardRest keyboardRest = new KeyboardRest();
            KeyboardOps keyboardOps= new KeyboardOps(keyboardOptional.get(),keyboardRest);
            logger.info("Keyboard fetched with Asset Id ->{}",refId);
            return keyboardOps.entityToRest();
        }
        else{
            throw new ResourceNotFoundException("Keyboard","refId",Long.toString(refId));
        }

    }

    @Override
    public void deleteByRefId(Long refId) {
        customRepository.deleteById(Keyboard.class,refId,"refId");
        logger.info("Keyboard deleted with Asset Id ->{}",refId);
    }

    @Override
    public void update(KeyboardRest keyboardRest) {
        Keyboard keyboard = new Keyboard();
        KeyboardOps keyboardOps = new KeyboardOps(keyboard,keyboardRest);
        customRepository.save(keyboardOps.restToEntity());
        logger.info("Keyboard Updated with Asset Id ->{}",keyboard.getRefId());
    }


    private void setCommands(){
        LinkedHashMap<String,String[]> commands = new LinkedHashMap<>();
        commands.put("cat /proc/bus/input/devices | awk -F= '/keyboard/ && NF==2 {print $2}'",new String[]{});
        LinuxCommandExecutorManager.add(Keyboard.class,commands);
    }
    private  List<String> getParseResult(){
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(Keyboard.class);
        List<String> list= new ArrayList<>();
        for (Map.Entry<String ,String[]> result: stringMap.entrySet()) {
            String[] values = result.getValue();
            for (int i = 0; i < values.length; i++) {
                list.add(values[i].replaceAll("\"", ""));

            }

        }
        return list;
    }
}
