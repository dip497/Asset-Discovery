package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.MotherBoard;
import com.serviceops.assetdiscovery.rest.MotherBoardRest;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

import java.util.*;

public class MotherBoardOps extends AssetBaseOps<MotherBoard, MotherBoardRest> {
    private final MotherBoard motherBoard;
    private final MotherBoardRest motherBoardRest;
    public MotherBoardOps(MotherBoard motherBoard, MotherBoardRest motherBoardRest) {
        super(motherBoard, motherBoardRest);
        this.motherBoard = motherBoard;
        this.motherBoardRest = motherBoardRest;
    }


    public MotherBoard restToEntity() {
         super.restToEntity(motherBoardRest);
         motherBoard.setVersion(motherBoardRest.getVersion());
         motherBoard.setInstalledDate(motherBoardRest.getInstalledDate());
         motherBoard.setPartNumber(motherBoardRest.getPartNumber());
         motherBoard.setPrimaryBusType(motherBoardRest.getPrimaryBusType());
         motherBoard.setSecondaryBusType(motherBoardRest.getSecondaryBusType());
         return motherBoard;

    }

    public MotherBoardRest entityToRest() {
         super.entityToRest(motherBoard);
        motherBoardRest.setVersion(motherBoard.getVersion());
        motherBoardRest.setInstalledDate(motherBoard.getInstalledDate());
        motherBoardRest.setPartNumber(motherBoard.getPartNumber());
        motherBoardRest.setPrimaryBusType(motherBoard.getPrimaryBusType());
        motherBoardRest.setSecondaryBusType(motherBoard.getSecondaryBusType());
        return motherBoardRest;
    }

    public static void setCommands(){
        LinkedHashMap<String,String[]> commands = new LinkedHashMap<>();
        commands.put("sudo dmidecode --string baseboard-manufacturer",new String[]{});
        commands.put("sudo dmidecode --string baseboard-serial-number",new String[]{});
        commands.put("sudo dmidecode --string baseboard-version",new String[]{});
        LinuxCommandExecutorManager.add(MotherBoard.class,commands);
    }
    public static List<String> getParseResult(){
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
