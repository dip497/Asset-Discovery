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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String[][] strings = parseResults();
        List<Keyboard> keyboards = customRepository.findAllByColumnName(Keyboard.class, "refId", id);
        if(!keyboards.isEmpty()) {
            if (strings[0][0].equals(String.valueOf(keyboards.size()))) {
                for (Keyboard keyboard : keyboards) {
                    for (String[] string : strings) {

                        keyboard.setRefId(id);
                        keyboard.setName(string[1]);
                        customRepository.save(keyboard);
                        logger.debug("Updated keyboard with Asset Id -> {}", id);
                    }

                }
            } else {
                for (Keyboard keyboard : keyboards) {
                    customRepository.deleteById(Keyboard.class, keyboard.getId(), "id");
                    logger.debug("deleted keyboard with Asset Id -> {}", id);
                }
                for (String[] string : strings) {
                    Keyboard keyboard = new Keyboard();
                    keyboard.setRefId(id);
                    keyboard.setName(string[1]);
                    customRepository.save(keyboard);
                    logger.debug("updated keyboard with Asset Id -> {}", id);
                }
            }
        } else {
            for (String[] string : strings) {
                Keyboard keyboard = new Keyboard();
                keyboard.setRefId(id);
                keyboard.setName(string[1]);
                customRepository.save(keyboard);
                logger.debug("saved keyboard with Asset Id-> {}" ,id);
            }
        }
    }

   /* @Override
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
            keyboard.setName(parseResult.get(0));
            customRepository.save(keyboard);
            logger.debug("Saved keyboard with refId -> {}", keyboard.getRefId());

        }

    }*/
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
    public List<KeyboardRest> findAllByRefId(Long refId) {
        List<Keyboard> fetchKeyboard = customRepository.findAllByColumnName(Keyboard.class, "refId", refId);
        if(fetchKeyboard.isEmpty()){
            logger.error("Keyboard not found with refId -> {} ",refId);
            return List.of();
            //throw new ResourceNotFoundException("KeyboardRest","refId",Long.toString(refId));
        }
        logger.info("fetched list of keyboard for refId ->{}", refId);
        return fetchKeyboard.stream().map(e->new KeyboardOps(e,new KeyboardRest()).entityToRest()).toList();
    }

    @Override
    public void deleteByRefId(Long refId, Long id) {
        List<Keyboard> keyboards = customRepository.findAllByColumnName(Keyboard.class, "refId", refId);
        if(keyboards.isEmpty()){
            throw new ResourceNotFoundException("keyboard","refId",refId.toString());
        }else{
            Optional<Keyboard> fetchKeyboard = customRepository.findByColumn("id", id, Keyboard.class);
            if(fetchKeyboard.isPresent()){
                customRepository.deleteById(Keyboard.class,id,"id");
                logger.info("Keyboard deleted of  Id ->{}",id);
            }else{
                throw new ResourceNotFoundException("Keyboard","Id",id.toString());

            }
        }
    }

    @Override
    public void update(Long id,KeyboardRest keyboardRest) {
        Optional<Keyboard> fetchKeyboard = customRepository.findByColumn("id", id, Keyboard.class);
        if(fetchKeyboard.isEmpty()){
            logger.error("Keyboard not found with Id -> {}" ,keyboardRest.getId());
            throw new ResourceNotFoundException("Keyboard","id",keyboardRest.getId().toString());
        }else{
            Keyboard keyboard = fetchKeyboard.get();
            KeyboardOps keyboardOps = new KeyboardOps(keyboard,keyboardRest);
            customRepository.save(keyboardOps.restToEntity());
            logger.info("Keyboard Updated with Asset Id ->{}",keyboard.getRefId());
        }
    }


    private void setCommands(){
        LinkedHashMap<String,String[]> commands = new LinkedHashMap<>();
        commands.put("cat /proc/bus/input/devices | awk -F= '/keyboard/ && NF==2 {print $2}' | wc -l",new String[]{});
        commands.put("cat /proc/bus/input/devices | awk -F= '/keyboard/ && NF==2 {print $2}'",new String[]{});
        LinuxCommandExecutorManager.add(Keyboard.class,commands);
    }
    private String[][] parseResults() {
        Map<String, String[]> commandResults = LinuxCommandExecutorManager.get(Keyboard.class);
        String[] strings = commandResults.get("cat /proc/bus/input/devices | awk -F= '/keyboard/ && NF==2 {print $2}' | wc -l");
        for (String string : strings) {
            System.out.println(string);
        }
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
        int keyboarCount = Integer.parseInt(strings[0]);
        String[][] parsedResult = new String[Integer.parseInt(strings[0].trim())][commandResults.size()];
        int j=0;
        for (Map.Entry<String, String[]> commandResult : commandResults.entrySet()) {
            String[] result = commandResult.getValue();
            if(keyboarCount<result.length){
                for(int i=1;i<result.length;i++){
                    if(result[i-1].trim().isEmpty()){
                        result[i-1]=result[i];
                    }
                }
            }
            if(j==0){
                parsedResult[0][j] = String.valueOf(strings[0].charAt(0)).trim();
                j++;
                continue;
            }
            for(int i=0;i<keyboarCount;i++){
                parsedResult[i][j] = result[i].replaceAll("\"","");
            }
            j++;
        }
        return parsedResult;
    }

/*    private  List<String> getParseResult(){
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(Keyboard.class);
        List<String> list= new ArrayList<>();
        for (Map.Entry<String ,String[]> result: stringMap.entrySet()) {
            String[] values = result.getValue();
            for (int i = 0; i < values.length; i++) {
                list.add(values[i].replaceAll("\"", ""));

            }

        }
        return list;
    }*/
}
