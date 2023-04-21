package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Asset;
import com.serviceops.assetdiscovery.exception.ComponentNotFoundException;
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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AssetServiceImpl implements AssetService {

    CustomRepository customRepository;
    private static final Logger logger = LoggerFactory.getLogger(AssetServiceImpl.class);

    public AssetServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        setCommands();
    }

    // Saving Asset in DB or Updating the details during Re-scan
    @Override
    public AssetRest save() {

        List<String> parseResult = getParseResult();
        Optional<Asset> optionalAsset =
                customRepository.findByColumn("ipAddress", parseResult.get(2), Asset.class);

        // If optionalAsset is present then do not add ip and updateByRefId the asset
        if (optionalAsset.isPresent()) {
            Asset updatedAsset = optionalAsset.get();
            setContent(parseResult, updatedAsset);
            logger.info("Updated asset with IP ->{}", parseResult.get(2));
            return findByIpAddress(updatedAsset.getIpAddress());
        }

        // If optionalAsset is not present then set ip address of asset and save as new asset
        else {
            Asset asset = new Asset();
            setContent(parseResult, asset);
            logger.info("Saved asset with IP ->{}", parseResult.get(2));
            return findByIpAddress(asset.getIpAddress());
        }

    }

    // Setting the Content in the Asset Entity.
    private void setContent(List<String> parseResult, Asset updatedAsset) {
        try {
            updatedAsset.setHostName(parseResult.get(0));
            updatedAsset.setDomainName(parseResult.get(1));
            updatedAsset.setIpAddress(parseResult.get(2));
            updatedAsset.setMacAddress(parseResult.get(3));
            updatedAsset.setSubNetMask(parseResult.get(4));
            updatedAsset.setAssetType("LINUX " + parseResult.get(5).toUpperCase());
            updatedAsset.setSerialNumber(parseResult.get(6));
            updatedAsset.setLastLoggedUser(parseResult.get(7));
            customRepository.save(updatedAsset);
        } catch (ArrayIndexOutOfBoundsException e) {
            customRepository.save(updatedAsset);
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

        // if optionalAsset is not present then throw ComponentNotFoundException
        else {
            logger.error("Asset not found by IP ->{}", ipAddress);
            throw new ComponentNotFoundException("AssetRest", "id", 0);
        }

    }

    // Finding Asset by ID
    @Override
    public AssetRest findById(long id) {

        Optional<Asset> optionalAsset = customRepository.findByColumn("id", id, Asset.class);

        // If optionalAsset is present then return the AssetRest
        if (optionalAsset.isPresent()) {
            AssetRest assetRest = new AssetRest();
            AssetOps assetOps = new AssetOps(optionalAsset.get(), assetRest);
            logger.info("Asset found by id ->{}", id);
            return assetOps.entityToRest();
        }

        // If optionalAsset is not present then throw ComponentNotFoundException
        else {
            logger.error("Asset not found by ID ->{}", id);
            throw new ComponentNotFoundException("AssetRest", "id", id);
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
    public void deleteById(long id) {

        // If Asset is present then move further to delete the Asset or else throw ComponentNotFoundException
        findById(id);

        // Deleting the Asset at given ID
        customRepository.deleteById(Asset.class, id, "id");

        logger.info("Asset deleted with id ->{}", id);

    }

    // Updating a particular field for Asset
    @Override
    public void update(long id, Map<String, Object> fields) {

        Optional<Asset> optionalAsset = customRepository.findByColumn("id", id, Asset.class);

        if (optionalAsset.isPresent()) {

            Asset asset = optionalAsset.get();

            // Fetching the filed from the Asset table using the concept of Reflection
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findRequiredField(Asset.class, key);
                ReflectionUtils.setField(field, asset, value);
            });

            // Updating the Asset by Changing the Asset Field
            customRepository.save(asset);

            logger.info("Updated Asset field -> {} for Asset id {}", fields, id);

        }

        // If Asset is not present then throw ComponentNotFoundException
        else {
            logger.error("Asset not found by ID ->{}", id);
            throw new ComponentNotFoundException("AssetRest", "id", id);
        }

    }

    // Setting the Commands to fetch Asset details
    private void setCommands() {

        // HashMap for setting the Multiple commands and their value in String[]
        LinkedHashMap<String, String[]> commands = new LinkedHashMap<>();

        // Command for getting the hostName.
        commands.put("hostname", new String[] {});

        // Command for getting the domain name.
        commands.put("domainname", new String[] {});

        // Command for getting the ip address, mac address and subnet-mask
        commands.put("sudo ip a ", new String[] {});

        // Asset Type
        commands.put("hostnamectl | grep Chassis | awk '{print $2}'", new String[] {});

        // Command for getting the serial number of device.
        commands.put("sudo dmidecode -s system-serial-number", new String[] {});

        // Command for getting the last logged-in user
        commands.put("w | tail -1 | awk '{print $1}'\n", new String[] {});

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

                    // Inserting the IP, MAC Address
                    if (values[i].contains(status)) {

                        String fetchingOutput =
                                values[i] + values[i + 1] + values[i + 2] + values[i + 3] + values[i + 4];

                        String mac = fetchingOutput.substring(fetchingOutput.indexOf("ether"),
                                fetchingOutput.indexOf("brd"));

                        String ipPartial1 = fetchingOutput.substring(fetchingOutput.indexOf("inet"));
                        String ipPartial2 =
                                ipPartial1.substring(ipPartial1.indexOf("inet"), ipPartial1.indexOf(" brd"));
                        String ip = ipPartial2.substring(5, ipPartial2.indexOf("/"));
                        String subnetMaskPartial = ipPartial2.substring(5);

                        String subnetMask = getSubnetMaskFromIp(subnetMaskPartial);

                        list.add(ip);
                        list.add(mac.substring(6).trim());
                        list.add(subnetMask);
                        flag = true;
                        break;

                    }

                    // Inserting the Subnet Mask
                    else if (values[i].contains(runningState)) {

                        String fetchingOutput = values[i] + values[i + 1];

                        String partialSubnetMask =
                                fetchingOutput.substring(fetchingOutput.indexOf(runningState));
                        String subnetMask = partialSubnetMask.substring(partialSubnetMask.indexOf("netmask"),
                                partialSubnetMask.indexOf("broadcast"));

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

    // Parsing the Subnet Mask from the ip address and suffix length
    private String getSubnetMaskFromIp(String ip) {

        String[] parts = ip.split("/");
        int prefixLength = Integer.parseInt(parts[1]);

        // Create an integer representation of the subnet mask
        int subnetMaskInt = 0xffffffff << (32 - prefixLength);

        // Convert the integer representation to a byte array
        byte[] subnetMaskBytes =
                new byte[] { (byte) ((subnetMaskInt >> 24) & 0xff), (byte) ((subnetMaskInt >> 16) & 0xff),
                        (byte) ((subnetMaskInt >> 8) & 0xff), (byte) (subnetMaskInt & 0xff) };

        try {
            // Create an InetAddress object from the subnet mask bytes
            InetAddress subnetMask = InetAddress.getByAddress(subnetMaskBytes);
            return subnetMask.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return null;

    }

}
