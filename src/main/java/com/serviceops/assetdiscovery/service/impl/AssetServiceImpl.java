package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Asset;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.service.interfaces.AssetService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.AssetOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AssetServiceImpl implements AssetService {

    CustomRepository customRepository;

    Logger logger = LoggerFactory.getLogger(AssetServiceImpl.class);

    public AssetServiceImpl(CustomRepository customRepository){
        this.customRepository = customRepository;
        setCommands();
    }


    @Override
    public AssetRest save() {
        List<String> parseResult = getParseResult();
        Optional<Asset> fetchAsset = customRepository.findByColumn("ipAddress", parseResult.get(2), Asset.class);
        if(fetchAsset.isPresent()) {
            Asset updatedAsset = fetchAsset.get();
            updatedAsset.setHostName(parseResult.get(0));
            updatedAsset.setDomainName(parseResult.get(1));
            updatedAsset.setMacAddress(parseResult.get(5));
            updatedAsset.setSubNetMask(parseResult.get(6));
            customRepository.save(updatedAsset);
            logger.debug("Updating asset with IP ->{}",parseResult.get(2));
            return findByIpAddress(updatedAsset.getIpAddress());
        }
        else {
            Asset asset = new Asset();
            asset.setHostName(parseResult.get(0));
            asset.setDomainName(parseResult.get(1));
            asset.setIpAddress(parseResult.get(2));
            asset.setAssetType(parseResult.get(3));
            asset.setSerialNumber(parseResult.get(4));
            asset.setMacAddress(parseResult.get(5));
            asset.setSubNetMask(parseResult.get(6));
            customRepository.save(asset);
            logger.debug("Saving asset with IP ->{}",parseResult.get(2));
            return findByIpAddress(asset.getIpAddress());
        }



    }

    @Override
    public AssetRest findByIpAddress(String ipAddress) {
        Asset asset = customRepository.findByColumn("ipAddress",ipAddress,Asset.class).get();
        AssetRest assetRest = new AssetRest();
        AssetOps assetOps = new AssetOps(asset,assetRest);
        return assetOps.entityToRest();
    }

    @Override
    public AssetRest findById(Long id) {
        Asset asset = customRepository.findByColumn("ipAddress",id.toString(),Asset.class).get();
        AssetRest assetRest = new AssetRest();
        AssetOps assetOps = new AssetOps(asset,assetRest);
        return assetOps.entityToRest();
    }

    @Override
    public List<AssetRest> findAll() {
        AssetOps assetOps = new AssetOps(new Asset(),new AssetRest());
        List<Asset> assets = customRepository.findAll(Asset.class);
        List<AssetRest> assetRests = new ArrayList<>();



        return assets.stream().map(assetOps::entityToRest).toList();
    }

    private  void setCommands(){

        // HashMap for setting the Multiple commands and their value in String[]
        LinkedHashMap<String,String[]> commands = new LinkedHashMap<>();

        // Command for getting the hostName.
        commands.put("hostname",new String[]{});

        // Command for getting the domain name.
        commands.put("domainname",new String[]{});

        // Command for getting the ip address.
        commands.put("ifconfig | grep 'inet ' | awk '{print $2}' | grep '^10\\.\\|^172\\.\\(1[6-9]\\|2[0-9]\\|3[01]\\)\\|^192\\.168\\.'",new String[]{});

        // Hardcoded command for getting the Asset Type.
        commands.put("sudo dmidecode -t system | grep \"Product Name\" | cut -d \" \" -f 3-",new String[]{});

        // Command for getting the serial number of device.
        commands.put("sudo dmidecode -s system-serial-number",new String[]{});

//         Command for getting the mac address.
        commands.put("ifconfig | grep -o -E '([[:xdigit:]]{1,2}:){5}[[:xdigit:]]{1,2}'",new String[]{});

        // Command for getting the subnet mask.
        commands.put("ifconfig $(ip route | grep default | awk '{print $5}') | awk '/netmask/{print $4}'",new String[]{});

        // Adding all the commands to the Main HasMap where the class Asset is the key for all the commands
        LinuxCommandExecutorManager.add(Asset.class,commands);
    }

    private  List<String> getParseResult(){
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(Asset.class);
        List<String> list= new ArrayList<>();
        for (Map.Entry<String ,String[]> result: stringMap.entrySet()) {
            String[] values = result.getValue();
            for (int i = 0; i < values.length; i++) {

                    list.add(values[i]);

            }
        }
        return list;
    }


}
