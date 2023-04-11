package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Asset;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.service.interfaces.AssetService;
import com.serviceops.assetdiscovery.utils.AllAssetResponse;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.AssetOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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
            logger.info("Updated asset with IP ->{}",parseResult.get(2));
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
            logger.info("Saved asset with IP ->{}",parseResult.get(2));
            return findByIpAddress(asset.getIpAddress());
        }

    }

    @Override
    public AssetRest findByIpAddress(String ipAddress) {
        Optional<Asset> assetOptional = customRepository.findByColumn("ipAddress",ipAddress,Asset.class);

        if(assetOptional.isPresent()) {
            AssetRest assetRest = new AssetRest();
            AssetOps assetOps = new AssetOps(assetOptional.get(), assetRest);
            logger.info("Asset found by IP ->{}", ipAddress);
            return assetOps.entityToRest();
        }else {
            throw new ResourceNotFoundException("AssetRest","ipAddress",ipAddress);
        }

    }

    @Override
    public AssetRest findById(Long id) {

        Optional<Asset> assetOptional = customRepository.findByColumn("id",id.toString(),Asset.class);

        if(assetOptional.isPresent()) {
            AssetRest assetRest = new AssetRest();
            AssetOps assetOps = new AssetOps(assetOptional.get(), assetRest);
            logger.info("Asset found by id ->{}", id);
            return assetOps.entityToRest();
        }else {
            throw new ResourceNotFoundException("AssetRest","id",Long.toString(id));
        }
    }

    @Override
    public  AllAssetResponse findPaginatedData(int pageNo,int pageSize,String sortBy,String sortDir) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Asset> assets = customRepository.findPaginatedData(pageable,sortBy,sortDir,Asset.class);
        List<AssetRest> assetRests = new ArrayList<>();

        for(Asset asset : assets){
            AssetRest rest = new AssetRest();
            AssetOps assetOps = new AssetOps(asset,rest);
            assetRests.add(assetOps.entityToRest());
        }

        int count = findTotalCount();

        AllAssetResponse data = new AllAssetResponse();

        data.setAssetRestList(assetRests);
        data.setPageNo(pageNo);
        data.setTotalElements(count);
        data.setPageSize(pageSize);

        logger.info("Assets found in {} order on {} page number {}",sortDir,sortBy,pageNo);

        return data;

    }

    @Override
    public int findTotalCount() {

        int count = customRepository.getCount(Asset.class);

        logger.info("Total Assets found -> {}",count);

        return count;
    }

    @Override
    public void deleteById(Long id) {

        customRepository.deleteById(Asset.class,id,"id");

        logger.info("Asset deleted with id ->{}",id);
    }

    @Override
    public void update(Long id,Map<String, Object> fields) {

        AssetRest assetRest = findById(id);

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findRequiredField(AssetRest.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, assetRest, value);
        });

        AssetOps assetOps = new AssetOps(new Asset(),assetRest);

        customRepository.update(assetOps.restToEntity());

        logger.info("Updated Asset field -> {} for Asset id {}",fields,id);

    }


    private  void setCommands(){

        // HashMap for setting the Multiple commands and their value in String[]
        LinkedHashMap<String,String[]> commands = new LinkedHashMap<>();

        // Command for getting the hostName.
        commands.put("hostname",new String[]{});

        // Command for getting the domain name.
        commands.put("domainname",new String[]{});

        // Command for getting the ip address.
        commands.put("sudo ip a | grep -Eo 'inet (addr:)?(10\\.|172\\.(1[6-9]|2[0-9]|3[01])\\.|192\\.168\\.)([0-9]*\\.){1,3}[0-9]*' | awk '{print $2}'",new String[]{});

        // Hardcoded command for getting the Asset Type.
        // uname -s
        commands.put("sudo dmidecode -t system | grep \"Product Name\" | cut -d \" \" -f 3-",new String[]{});

        // Command for getting the serial number of device.
        commands.put("sudo dmidecode -s system-serial-number",new String[]{});

        // Command for getting the mac address.
        commands.put("sudo ifconfig | grep -o -E '([[:xdigit:]]{1,2}:){5}[[:xdigit:]]{1,2}' | head -n 1",new String[]{});

        // Command for getting the subnet mask.
        commands.put("sudo ifconfig $(ip route | grep default | awk '{print $5}') | awk '/netmask/{print $4}'",new String[]{});

        // Adding all the commands to the Main HasMap where the class Asset is the key for all the commands
        LinuxCommandExecutorManager.add(Asset.class,commands);
    }

    private  List<String> getParseResult(){
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(Asset.class);
        List<String> list= new ArrayList<>();
        for (Map.Entry<String ,String[]> result: stringMap.entrySet()) {
            String[] values = result.getValue();
            for (String value : values) {
                if (value == null)
                    continue;
                list.add(value);

            }
        }
        return list;
    }

}
