package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Keyboard;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.service.interfaces.KeyboardService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class KeyboardServiceImpl implements KeyboardService {
    private final CustomRepository customRepository;

    public KeyboardServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
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
                list.add(values[i]);

            }

        }
        return list;
    }

    @Override
    public void save(Long id) {
        Optional<Keyboard> optionalKeyboard = customRepository.findByColumn("refId", id, Keyboard.class);
        if(optionalKeyboard.isPresent()){
            List<String> parseResult = getParseResult();
            Keyboard keyboard = optionalKeyboard.get();
            keyboard.setName(parseResult.get(0));
            customRepository.save(keyboard);
        }else{
            Keyboard keyboard = new Keyboard();
            keyboard.setRefId(id);
            List<String> parseResult = getParseResult();
            keyboard.setManufacturer(parseResult.get(0));
            customRepository.save(keyboard);
        }

    }
}
