package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.ComputerSystem;
import com.serviceops.assetdiscovery.rest.ComputerSystemRest;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;
import org.springframework.stereotype.Component;

import java.util.*;


public class ComputerSystemOps extends AssetBaseOps<ComputerSystem, ComputerSystemRest> {

    private static ComputerSystem computerSystem;
    private static ComputerSystemRest computerSystemRest;

    public ComputerSystemOps(ComputerSystem computerSystem, ComputerSystemRest computerSystemRest) {
        super(computerSystem, computerSystemRest);
        this.computerSystem = computerSystem;
        this.computerSystemRest = computerSystemRest;
    }


    public ComputerSystem restToEntity() {
        super.restToEntity(computerSystemRest);
        computerSystem.setModelName(computerSystemRest.getModelName());
        computerSystem.setSystemType(computerSystemRest.getSystemType());
        computerSystem.setPcSystemType(computerSystemRest.getPcSystemType());
        computerSystem.setUuid(computerSystemRest.getUuid());
        computerSystem.setBootUpState(computerSystemRest.getBootUpState());
        computerSystem.setPartOfDomian(computerSystemRest.getPartOfDomian());
        computerSystem.setUserName(computerSystemRest.getUserName());
        computerSystem.setDescription(computerSystemRest.getDescription());

        return computerSystem;
    }

    public ComputerSystemRest entityToRest() {
        super.entityToRest(computerSystem);
        computerSystemRest.setModelName(computerSystem.getModelName());
        computerSystemRest.setSystemType(computerSystem.getSystemType());
        computerSystemRest.setPcSystemType(computerSystem.getPcSystemType());
        computerSystemRest.setUuid(computerSystem.getUuid());
        computerSystemRest.setBootUpState(computerSystem.getBootUpState());
        computerSystemRest.setPartOfDomian(computerSystem.getPartOfDomian());
        computerSystemRest.setUserName(computerSystem.getUserName());
        computerSystemRest.setDescription(computerSystem.getDescription());

        return computerSystemRest;
    }

    public static void setCommands() {
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();
        commands.put("whoami", new String[]{});
        commands.put("sudo dmidecode -s system-product-name", new String[]{});
        commands.put("uname -m", new String[]{});
//        commands.put() set to be later
        commands.put("sudo dmidecode -s system-uuid", new String[]{});
        commands.put("systemctl is-system-running", new String[]{});
        commands.put("sudo dmidecode -s system-manufacturer", new String[]{});
        LinuxCommandExecutorManager.add(ComputerSystem.class, commands);
    }

    public static List<String> getParseResults() {
        Map<String, String[]> commandResults = LinuxCommandExecutorManager.get(ComputerSystem.class);
        List<String> parsedResults = new ArrayList<>();
        for (Map.Entry<String, String[]> commandResult : commandResults.entrySet()) {
            String[] results = commandResult.getValue();
            for (int i=0;i< results.length;i++) {
                if(results[i].contains("admin"))
                    i++;
                else if(results[i].isEmpty())
                    parsedResults.add(null);
                else if(results[i].startsWith("Manu")){
                    parsedResults.add(results[i].trim().substring(14, results[i].length()).trim());
                }else {
                    parsedResults.add(results[i]);
                }
            }
        }
        return parsedResults;
    }

}
