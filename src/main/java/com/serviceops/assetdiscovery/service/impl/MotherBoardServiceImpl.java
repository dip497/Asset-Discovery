package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.MotherBoard;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.MotherBoardRest;
import com.serviceops.assetdiscovery.service.interfaces.MotherBoardService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MotherBoardServiceImpl implements MotherBoardService{
    private final CustomRepository customRepository;

    public MotherBoardServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }
    @Override
    public void  save(Long id ) {
        Optional<MotherBoard> optionalfetchMotherBoard = customRepository.findByColumn("id", id, MotherBoard.class);
        if(optionalfetchMotherBoard.isPresent()){
            List<String> parseResult = getParseResult();
            MotherBoard fetchMotherBoard = optionalfetchMotherBoard.get();
            fetchMotherBoard.setManufacturer(parseResult.get(0));
            fetchMotherBoard.setSerialNumber(parseResult.get(1));
            fetchMotherBoard.setVersion(parseResult.get(2));
            customRepository.save(fetchMotherBoard);
        }else{
            MotherBoard motherBoard = new MotherBoard();
            motherBoard.setRefId(id);
            List<String> parseResult = getParseResult();
            motherBoard.setManufacturer(parseResult.get(0));
            motherBoard.setSerialNumber(parseResult.get(1));
            motherBoard.setVersion(parseResult.get(2));
            customRepository.save(motherBoard);
        }
    }

    @Override
    public MotherBoardRest getMotherBoard(Long id) {
        return null;
    }

    private void setCommands(){
        LinkedHashMap<String,String[]> commands = new LinkedHashMap<>();
        commands.put("sudo dmidecode --string baseboard-manufacturer",new String[]{});
        commands.put("sudo dmidecode --string baseboard-serial-number",new String[]{});
        commands.put("sudo dmidecode --string baseboard-version",new String[]{});
        LinuxCommandExecutorManager.add(MotherBoard.class,commands);
    }
    private  List<String> getParseResult(){
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(MotherBoard.class);
        List<String> list= new ArrayList<>();
        for (Map.Entry<String ,String[]> result: stringMap.entrySet()) {
            String[] values = result.getValue();
            for (int i = 2; i < values.length; i+=2) {
                list.add(values[i]);

            }

        }
        return list;
    }

}
