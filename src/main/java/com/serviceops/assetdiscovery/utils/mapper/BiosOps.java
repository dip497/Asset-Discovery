package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Bios;
import com.serviceops.assetdiscovery.entity.MotherBoard;
import com.serviceops.assetdiscovery.rest.BiosRest;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiosOps extends AssetBaseOps<Bios, BiosRest> {

    private final Bios bios;
    private final BiosRest biosRest;

    public BiosOps(Bios bios, BiosRest biosRest) {
        super(bios, biosRest);
        this.bios = bios;
        this.biosRest = biosRest;
    }

    @Override
    public Bios restToEntity(BiosRest biosRest) {
        super.restToEntity(biosRest);
        bios.setName(biosRest.getName());
        bios.setSmBiosVersion(biosRest.getSmBiosVersion());
        bios.setRealeaseDate(biosRest.getRealeaseDate());
        bios.setVersion(biosRest.getVersion());
        bios.setDescription(biosRest.getDescription());
        return bios;
    }

    @Override
    public BiosRest entityToRest(Bios bios) {
        super.entityToRest(bios);
        biosRest.setName(bios.getName());
        biosRest.setSmBiosVersion(bios.getSmBiosVersion());
        biosRest.setRealeaseDate(bios.getRealeaseDate());
        biosRest.setVersion(bios.getVersion());
        biosRest.setDescription(bios.getDescription());
        return biosRest;
    }

    public static void setCommands(){
        HashMap<String,String[]> commands = new HashMap<>();
        commands.put("sudo dmidecode -s bios-release-date",new String[]{});
        commands.put("sudo dmidecode -s bios-version",new String[]{});
        commands.put("sudo dmidecode | awk '/SMBIOS/ {print $2}' FS='[ .]+' OFS='.'",new String[]{}); // extract the second string from the array
    }

    public static List<String> getParseResult(){
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(Bios.class);
        List<String> list= new ArrayList<>();
        for (Map.Entry<String ,String[]> result: stringMap.entrySet()) {
            String[] values = result.getValue();
            for (String value: values) {
                if(value.isEmpty()){
                    list.add(null);
                }
                else {
                    list.add(value);
                }
            }
        }
        return list;
    }

}
