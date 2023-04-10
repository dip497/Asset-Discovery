package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Asset;
import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssetOps extends SingleBaseOps<Asset,AssetRest> {

    private final Asset asset;
    private final AssetRest assetRest;

    public AssetOps(Asset asset, AssetRest assetRest) {
        super(asset, assetRest);
        this.asset = asset;
        this.assetRest = assetRest;
    }
    public Asset restToEntity(){
        super.restToEntity(assetRest);
        asset.setHostName(assetRest.getHostName());
        asset.setDomainName(assetRest.getDomainName());
        asset.setIpAddress(assetRest.getIpAddress());
        asset.setAssetType(assetRest.getAssetType());
        asset.setSerialNumber(assetRest.getSerialNumber());
        asset.setMacAddress(assetRest.getMacAddress());
        asset.setSubNetMask(assetRest.getSubNetMask());
        return asset;
    }

    public AssetRest entityToRest(){
        super.entityToRest(asset);
        assetRest.setHostName(asset.getHostName());
        assetRest.setDomainName(asset.getDomainName());
        assetRest.setIpAddress(asset.getIpAddress());
        assetRest.setAssetType(asset.getAssetType());
        assetRest.setSerialNumber(asset.getSerialNumber());
        assetRest.setMacAddress(asset.getMacAddress());
        assetRest.setSubNetMask(assetRest.getSubNetMask());
        return assetRest;
    }

    public static void setCommands(){

        // HashMap for setting the Multiple commands and their value in String[]
        HashMap<String,String[]> commands = new HashMap<>();

        // Command for getting the hostName.
        commands.put("hostname",new String[]{});

        // Command for getting the domain name.
        commands.put("domainname",new String[]{});

        // Command for getting the ip address.
        commands.put("ifconfig | grep 'inet ' | awk '{print $2}' | grep '^10\\.\\|^172\\.\\(1[6-9]\\|2[0-9]\\|3[01]\\)\\|^192\\.168\\.'",new String[]{});

        // Hardcoded command for getting the Asset Type.
        commands.put("",new String[]{"Hardware"});

        // Command for getting the serial number of device.
        commands.put("sudo dmidecode -s system-serial-number",new String[]{});

        // Command for getting the mac address.
        commands.put("ifconfig wlp2s0 | grep -oE '([[:xdigit:]]{1,2}:){5}[[:xdigit:]]{1,2}'",new String[]{});

        // Command for getting the subnet mask.
        commands.put("ifconfig <interface_name> | awk '/netmask/{print $4}'",new String[]{});

        // Adding all the commands to the Main HasMap where the class Asset is the key for all the commands
        LinuxCommandExecutorManager.add(Asset.class,commands);
    }

    public static List<String> getParseResult(){
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(Asset.class);
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
