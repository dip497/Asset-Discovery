package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Asset;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.AllAssetRest;
import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.service.interfaces.AssetService;
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

    public AssetServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }

    // Saving Asset in DB or Updating the details during Re-scan
    @Override
    public AssetRest save() {

        List<String> parseResult = getParseResult();
        Optional<Asset> optionalAsset = customRepository.findByColumn("ipAddress", parseResult.get(2), Asset.class);

        // If optionalAsset is present then do not add ip and update the asset
        if (optionalAsset.isPresent()) {
            Asset updatedAsset = optionalAsset.get();
            updatedAsset.setHostName(parseResult.get(0));
            updatedAsset.setDomainName(parseResult.get(1));
            updatedAsset.setMacAddress(parseResult.get(3));
            updatedAsset.setSubNetMask(parseResult.get(6));
            customRepository.save(updatedAsset);
            logger.info("Updated asset with IP ->{}", parseResult.get(2));
            return findByIpAddress(updatedAsset.getIpAddress());
        }

        // If optionalAsset is not present then set ip address of asset and save as new asset
        else {
            Asset asset = new Asset();
            asset.setHostName(parseResult.get(0));
            asset.setDomainName(parseResult.get(1));
            asset.setIpAddress(parseResult.get(2));
            asset.setMacAddress(parseResult.get(3));
            asset.setAssetType("LINUX "+parseResult.get(4).toUpperCase());
            asset.setSerialNumber(parseResult.get(5));
            asset.setSubNetMask(parseResult.get(6));
            customRepository.save(asset);
            logger.info("Saved asset with IP ->{}", parseResult.get(2));
            return findByIpAddress(asset.getIpAddress());
        }

    }

    // Finding Asset by IP
    @Override
    public AssetRest findByIpAddress(String ipAddress) {

        Optional<Asset> optionalAsset = customRepository.findByColumn("ipAddress", ipAddress, Asset.class);

        // If optionalAsset is present then return the AssetRest.
        if (optionalAsset.isPresent()) {
            AssetRest assetRest = new AssetRest();
            AssetOps assetOps = new AssetOps(optionalAsset.get(), assetRest);
            logger.info("Asset found by IP ->{}", ipAddress);
            return assetOps.entityToRest();
        }

        // if optionalAsset is not present then throw ResourceNotFoundException
        else {
            logger.error("Asset not found by IP ->{}", ipAddress);
            throw new ResourceNotFoundException("AssetRest", "ipAddress", ipAddress);
        }

    }

    // Finding Asset by ID
    @Override
    public AssetRest findById(Long id) {

        Optional<Asset> optionalAsset = customRepository.findByColumn("id", id.toString(), Asset.class);

        // If optionalAsset is present then return the AssetRest
        if (optionalAsset.isPresent()) {
            AssetRest assetRest = new AssetRest();
            AssetOps assetOps = new AssetOps(optionalAsset.get(), assetRest);
            logger.info("Asset found by id ->{}", id);
            return assetOps.entityToRest();
        }

        // If optionalAsset is not present then throw ResourceNotFoundException
        else {
            logger.error("Asset not found by ID ->{}", id);
            throw new ResourceNotFoundException("AssetRest", "id", Long.toString(id));
        }

    }

    // Finding Assets based on Paginated data.
    @Override
    public AllAssetRest findPaginatedData(int pageNo, int pageSize, String sortBy, String sortDir) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Asset> assets = customRepository.findPaginatedData(pageable, sortBy, sortDir, Asset.class);
        List<AssetRest> assetRests = new ArrayList<>();

        // Converting the Asset to AssetRest
        for (Asset asset : assets) {
            AssetRest rest = new AssetRest();
            AssetOps assetOps = new AssetOps(asset, rest);
            assetRests.add(assetOps.entityToRest());
        }

        // Fetching the Total number of Assets
        int count = findTotalCount();

        // Setting the AllAssetRest response
        AllAssetRest data = new AllAssetRest();
        data.setAssetRestList(assetRests);
        data.setPageNo(pageNo);
        data.setTotalElements(count);
        data.setPageSize(pageSize);

        logger.info("Assets found in {} order on {} page number {}", sortDir, sortBy, pageNo);

        // Return the AllAssetRest response
        return data;

    }

    // Finding total number of Assets
    @Override
    public int findTotalCount() {

        // Fetching the total number of Assets
        int count = customRepository.getCount(Asset.class);

        logger.info("Total Assets found -> {}", count);

        // Returning the count of assets
        return count;
    }

    // Deleting Asset by Id
    @Override
    public void deleteById(Long id) {

        // If Asset is present then move further to delete the Asset or else throw ResourceNotFoundException
        findById(id);

        // Deleting the Asset at given ID
        customRepository.deleteById(Asset.class, id, "id");

        logger.info("Asset deleted with id ->{}", id);

    }

    // Updating a particular field for Asset
    @Override
    public void update(Long id, Map<String, Object> fields) {

        Optional<Asset> optionalAsset = customRepository.findByColumn("id", id.toString(), Asset.class);

        if (optionalAsset.isPresent()) {

            Asset asset = optionalAsset.get();

            // Fetching the filed from the Asset table using the concept of Reflection
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findRequiredField(Asset.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, asset, value);
            });

            // Updating the Asset by Changing the Asset Field
            customRepository.save(asset);

            logger.info("Updated Asset field -> {} for Asset id {}", fields, id);

        }

        // If Asset is not present then throw ResourceNotFoundException
        else{
            logger.error("Asset not found by ID ->{}", id);
            throw new ResourceNotFoundException("AssetRest", "id", Long.toString(id));
        }

    }

    // Setting the Commands to fetch Asset details
    private void setCommands() {

        // HashMap for setting the Multiple commands and their value in String[]
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();

        // Command for getting the hostName.
        commands.put("hostname", new String[]{});

        // Command for getting the domain name.
        commands.put("domainname", new String[]{});

        // Command for getting the ip address.
        commands.put("sudo ip a ", new String[]{});

        // Asset Type
        commands.put("hostnamectl | grep Chassis | awk '{print $2}'", new String[]{});

        // Command for getting the serial number of device.
        commands.put("sudo dmidecode -s system-serial-number", new String[]{});

        // Command for getting the subnet mask.
        commands.put("sudo ifconfig", new String[]{});

        // Adding all the commands to the Main HasMap where the class Asset is the key for all the commands
        LinuxCommandExecutorManager.add(Asset.class, commands);
    }

    // Parsing Data for Asset
    private List<String> getParseResult() {
        Map<String, String[]> stringMap = LinuxCommandExecutorManager.get(Asset.class);
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String[]> result : stringMap.entrySet()) {
            boolean flag = false;
            String[] values = result.getValue();

            // If the values array is empty then add null
            if (values.length == 0) {
                flag = true;
                list.add(null);
            }

            // If values array has length less-then 4
            // Check at which index the result is and add it in the List
            else if (values.length < 4) {
                for (String ans : values) {
                    if (!ans.trim().isEmpty()) {
                        list.add(ans);
                        flag = true;
                    }
                }
            }

            // If the values length is larger than 4 check for multiple parameters
            else {

                String status = "state UP";
                String runningState = "<UP,BROADCAST,RUNNING,MULTICAST>";

                for (int i = 0; i < values.length; ++i) {

                    // Inserting the IP and MAC Address
                    if (values[i].contains(status)) {

                        String fetchingOutput = values[i] + values[i + 1] + values[i + 2] + values[i + 3] + values[i + 4];

                        String mac = fetchingOutput.substring(fetchingOutput.indexOf("ether"), fetchingOutput.indexOf("brd"));

                        String ipPartial = fetchingOutput.substring(fetchingOutput.indexOf("inet"));
                        String ip = ipPartial.substring(ipPartial.indexOf("inet"), ipPartial.indexOf("/"));

                        list.add(ip.substring(5).trim());
                        list.add(mac.substring(6).trim());
                        flag = true;
                        break;

                    }

                    // Inserting the Subnet Mask
                    else if (values[i].contains(runningState)) {

                        String fetchingOutput = values[i] + values[i + 1];

                        String partialSubnetMask = fetchingOutput.substring(fetchingOutput.indexOf(runningState));
                        String subnetMask = partialSubnetMask.substring(partialSubnetMask.indexOf("netmask"), partialSubnetMask.indexOf("broadcast"));

                        list.add(subnetMask.substring(8));
                        flag = true;
                        break;

                    }

                }

            }
            if (!flag) {
                list.add(null);
            }

        }

        return list;
    }

}
