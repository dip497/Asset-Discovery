package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.NetworkScan;
import com.serviceops.assetdiscovery.entity.enums.IpRangeType;
import com.serviceops.assetdiscovery.exception.AssetDiscoveryApiException;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.repository.PersistToDB;
import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.rest.NetworkScanRest;
import com.serviceops.assetdiscovery.service.interfaces.CredentialsService;
import com.serviceops.assetdiscovery.service.interfaces.NetworkScanService;
import com.serviceops.assetdiscovery.service.interfaces.SchedulersService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.NetworkScanOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

@Service
public class NetworkScanServiceImpl implements NetworkScanService {
    private final CustomRepository customRepository;
    private final CredentialsService credentialsService;
    private final PersistToDB persistToDB;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public NetworkScanServiceImpl(CustomRepository customRepository, CredentialsService credentialsService,
            PersistToDB persistToDB) {
        this.customRepository = customRepository;
        this.credentialsService = credentialsService;
        this.persistToDB = persistToDB;

    }

    private static String getNetworkAddress(String ipAddress) {
        String[] octets = ipAddress.split("\\.");
        if (octets.length != 4) {
            return null;
        }
        return octets[0] + "." + octets[1] + ".";
    }

    @Override
    public NetworkScanRest findById(Long id) {
        Optional<NetworkScan> fetchNetworkScan = customRepository.findByColumn("id", id, NetworkScan.class);
        if (fetchNetworkScan.isPresent()) {
            NetworkScan networkScan = fetchNetworkScan.get();
            logger.info("NetworkScan found by id -> {}", id);
            return new NetworkScanOps(networkScan, new NetworkScanRest()).entityToRest();
        } else {
            logger.error("NetworkScanJob not found by id -> {}", id);
            throw new ResourceNotFoundException("NetworkScan", "id", id.toString());
        }
    }

    @Override
    public void save(NetworkScanRest networkScanRest) {
        Optional<NetworkScan> networkScan =
                customRepository.findByColumn("id", networkScanRest.getId(), NetworkScan.class);
        if (networkScan.isPresent()) {
            customRepository.save(new NetworkScanOps(networkScan.get(), networkScanRest).restToEntity());
            logger.info("NetworkScanJob updated by id -> {}", networkScan.get().getId());
        } else {
            networkScanRest.setEnabled(true);
            customRepository.save(new NetworkScanOps(new NetworkScan(), networkScanRest).restToEntity());
            logger.info("NetworkScanJob saved by id -> {}", networkScanRest.getId());

        }

    }

    @Override
    public List<NetworkScanRest> findAll() {
        List<NetworkScanRest> list = customRepository.findAll(NetworkScan.class).stream()
                .map(n -> new NetworkScanOps(n, new NetworkScanRest()).entityToRest()).toList();
        logger.info("network scan find all fetched");
        return list;
    }

    @Override
    public void deleteById(Long id) {
        customRepository.deleteById(NetworkScan.class, id, "id");
        logger.info("network scan delete with id -> {}", id);
    }

    @Override
    public void update(Long id, NetworkScanRest networkScanRest) {
        Optional<NetworkScan> fetchNetworkScan = customRepository.findByColumn("id", id, NetworkScan.class);
        if (fetchNetworkScan.isEmpty()) {
            logger.error("networkScan not exist with id ->{}", id);
            throw new ResourceNotFoundException("NetworkScanJob", "id", id.toString());
        } else {
            NetworkScan networkScan = fetchNetworkScan.get();
            networkScan = new NetworkScanOps(networkScan, networkScanRest).restToEntity();
            customRepository.save(networkScan);
            logger.info("network scan updated -> {}", networkScan.getId());
        }
    }

    @Override
    public void scan(Long id) {
        Optional<NetworkScan> fetchNetworkScan = customRepository.findByColumn("id", id, NetworkScan.class);
        if (fetchNetworkScan.isPresent()) {
            NetworkScan networkScan = fetchNetworkScan.get();
            NetworkScanRest networkScanRest = new NetworkScanRest();
            networkScanRest = new NetworkScanOps(networkScan, networkScanRest).entityToRest();
            if (networkScan.getIpRangeType() == IpRangeType.ENTIRE_NETWORK) {
                performScanOnCredentialEntireNetwork(networkScanRest);
            } else {
                performScanOnCredentialSpecificSetOfIp(networkScanRest);
            }
        } else {
            logger.error("Network scan not found with id ->{} " , id);
            throw new ResourceNotFoundException("NetworkScanJob", "id", id.toString());
        }
    }

    private void performScanOnCredentialEntireNetwork(NetworkScanRest networkScanRest) {
        String NETWORK_ADDRESS = getNetworkAddress(networkScanRest.getIpRangeStart());
        List<Long> credentialsIds = networkScanRest.getCredentialId();
        List<CredentialsRest> credentialsList =
                credentialsIds.stream().map(credentialsService::findById).toList();
        int thirdOctet = Integer.parseInt(networkScanRest.getIpRangeStart().split("\\.")[2]);
        int fourthOctet = Integer.parseInt(networkScanRest.getIpRangeStart().split("\\.")[3]);
        for (int i = thirdOctet; i <= 255; i++) {
            for (int j = fourthOctet; j <= 255; j++) {
                String ipAddress = NETWORK_ADDRESS + i + "." + j;
                if (pingIp(ipAddress)) {
                    for (CredentialsRest credential : credentialsList) {
                        logger.debug("trying to connect using ->{}", credential.getUsername());
                        if (fetch(ipAddress, credential.getUsername(), credential.getPassword())) {
                            logger.debug("fetch completed using ->{}", credential.getUsername());
                            break;
                        }
                    }
                } else {
                    logger.error("Could not ping ->{}", ipAddress);
                }
            }
            fourthOctet = 0;
        }
    }

    private void performScanOnCredentialSpecificSetOfIp(NetworkScanRest networkScanRest) {
        List<String> IP_ADDRESS_LIST = networkScanRest.getIpList();
        List<Long> credentialsIds = networkScanRest.getCredentialId();
        List<CredentialsRest> credentialsList =
                credentialsIds.stream().map(credentialsService::findById).toList();
        for (String ip : IP_ADDRESS_LIST) {
            for (CredentialsRest credential : credentialsList) {
                logger.debug("trying to connect using ->{}", credential.getUsername());
                if (fetch(ip, credential.getUsername(), credential.getPassword())) {
                    logger.debug("fetch completed using ->{}", credential.getUsername());
                    break;
                }
            }
        }

    }


    private boolean fetch(String ipAddress, String username, String password) {
        try {

            logger.debug("Scanning -> {} ", ipAddress);
            new LinuxCommandExecutorManager(ipAddress, username, password, 22).fetch();
            persistToDB.saveToDB();
            return true;
        } catch (AssetDiscoveryApiException e) {
            logger.error("Network Scanner Error for host-> {}", ipAddress);
            return false;
        }

    }

    private boolean pingIp(String ipAddress) {

        InetAddress inet = null;
        try {
            inet = InetAddress.getByName(ipAddress);
            return inet.isReachable(1000);
        } catch (UnknownHostException e) {
            logger.warn("unknown host ->{} with message ->{}", inet, e.getMessage());
        } catch (IOException e) {
            logger.warn("fail to ping ->{}'", e.getMessage());
        }

        return false;
    }


}
