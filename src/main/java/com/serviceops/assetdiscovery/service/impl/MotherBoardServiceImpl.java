package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.MotherBoard;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.MotherBoardRest;
import com.serviceops.assetdiscovery.service.interfaces.MotherBoardService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.MotherBoardOps;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MotherBoardServiceImpl implements MotherBoardService{
    private MotherBoardOps  motherBoardOps;
    private CustomRepository customRepository;

    public MotherBoardServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }
    @Override
    public void  save(Long id ) {
        MotherBoard motherBoard = new MotherBoard();
        motherBoard.setRefId(id);
        List<String> parseResult = getParseResult();
        motherBoard.setManufacturer(parseResult.get(0));
        motherBoard.setSerialNumber(parseResult.get(1));
        motherBoard.setVersion(parseResult.get(2));
        customRepository.save(motherBoard);
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
