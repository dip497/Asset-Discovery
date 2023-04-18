package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Credentials;
import com.serviceops.assetdiscovery.entity.Scheduler;
import com.serviceops.assetdiscovery.entity.enums.IpRangeType;
import com.serviceops.assetdiscovery.entity.NetworkScan;
import com.serviceops.assetdiscovery.entity.enums.ScanType;
import com.serviceops.assetdiscovery.exception.AssetDiscoveryApiException;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.repository.PersistToDB;
import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.rest.NetworkScanRest;
import com.serviceops.assetdiscovery.rest.SchedulerRest;
import com.serviceops.assetdiscovery.service.interfaces.*;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutor;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.NetworkScanOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.Optional;

@Service
public class NetworkScanServiceImpl implements NetworkScanService {
    private final CustomRepository customRepository;
    private final CredentialsService credentialsService;
    private final SchedulerService schedulerService;
    private final PersistToDB persistToDB;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public NetworkScanServiceImpl(CustomRepository customRepository, CredentialsService credentialsService, SchedulerService schedulerService, PersistToDB persistToDB) {
        this.customRepository = customRepository;
        this.credentialsService = credentialsService;
        this.schedulerService = schedulerService;
        this.persistToDB = persistToDB;

    }


    @Override
    public NetworkScanRest findById(Long id) {
        Optional<NetworkScan> fetchNetworkScan = customRepository.findByColumn("id", id, NetworkScan.class);
        if(fetchNetworkScan.isPresent()){
            NetworkScan networkScan = fetchNetworkScan.get();
            logger.info("NetworkScan found by id -> {}", id);
            return new NetworkScanOps(networkScan,new NetworkScanRest()).entityToRest();
        }else{
            logger.error("NetworkScan not found by id -> {}", id);
            throw new ResourceNotFoundException("NetworkScan","id",id.toString());
        }
    }

    @Override
    public void save(NetworkScanRest networkScanRest) {
        Optional<NetworkScan> networkScan = customRepository.findByColumn("id", networkScanRest.getId(), NetworkScan.class);
        if(networkScan.isPresent()){
            customRepository.save(new NetworkScanOps(networkScan.get(),networkScanRest).restToEntity());
        }else{
            networkScanRest.setEnabled(true);
            customRepository.save(new NetworkScanOps(new NetworkScan(),networkScanRest).restToEntity());

        }

    }

    @Override
    public List<NetworkScanRest> findAll() {
        logger.info("network scan find all fetched");
        return customRepository.findAll(NetworkScan.class).stream().map(n->new NetworkScanOps(n,new NetworkScanRest()).entityToRest()).toList();
    }

    @Override
    public void deleteById(Long id) {

        customRepository.deleteById(NetworkScan.class,id,"id");
    }

    @Override
    public void update(Long id, NetworkScanRest networkScanRest) {
        Optional<NetworkScan> networkScan = customRepository.findByColumn("id", id, NetworkScan.class);
        if(networkScan.isEmpty()){
            throw  new ResourceNotFoundException("NetworkScan","id",id.toString());
        }else{
            customRepository.update(new NetworkScanOps(networkScan.get(),networkScanRest).restToEntity());
            logger.info("network scan updated -> {}", networkScan.get());
        }
    }

    @Override
    public void scan(Long id) {
        Optional<NetworkScan> fetchNetworkScan = customRepository.findByColumn("id", id, NetworkScan.class);
        if (fetchNetworkScan.isPresent()) {
            NetworkScan networkScan = fetchNetworkScan.get();
            if(networkScan.getScanType() == ScanType.NOT_SCHEDULED){
                NetworkScanRest networkScanRest =  new NetworkScanRest();
                networkScanRest =new NetworkScanOps(networkScan,networkScanRest).entityToRest();
                if(networkScan.getIpRangeType() == IpRangeType.ENTIRE_NETWORK){
                    performScanOnCredentialEntireNetwork(networkScanRest);
                }else{
                    performScanOnCredentialSpecificSetOfIp(networkScanRest);

                }
            }else{
                try {
                    logger.error("Cant run Schedule scan now -> {}",networkScan.getScanType());
                    throw new AssetDiscoveryApiException("Cant run schedule scan now for "+networkScan.getScanType());
                } catch (AssetDiscoveryApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }else{
            throw new  ResourceNotFoundException("NetworkScan","id",id.toString());
        }
    }

    @Override
    public void addScheduler(Long id, SchedulerRest schedulerRest) {
        NetworkScanRest networkScan = findById(id);
        Scheduler scheduler = schedulerService.save(schedulerRest);
        networkScan.setSchedulerRefId(scheduler.getId());
        customRepository.update(networkScan);
    }
    private static String getNetworkAddress(String ipAddress) {
        String[] octets = ipAddress.split("\\.");
        if (octets.length != 4) {
            return null;
        }
        return octets[0] + "." + octets[1] + ".";
    }


    private void performScanOnCredentialEntireNetwork(NetworkScanRest networkScanRest){
        String NETWORK_ADDRESS = getNetworkAddress(networkScanRest.getIpRangeStart());
        List<Long> credentialsIds = networkScanRest.getRefIds();
        List<CredentialsRest> credentialsList = credentialsIds.stream().map(credentialsService::findById).toList();
        int thirdOctet = Integer.parseInt(networkScanRest.getIpRangeStart().split("\\.")[2]);
        int  fourthOctet = Integer.parseInt(networkScanRest.getIpRangeStart().split("\\.")[3]);
        for (int i = thirdOctet; i <= 255; i++) {
            for (int j = fourthOctet; j <= 255; j++) {
                String ipAddress = NETWORK_ADDRESS + i+"." + j;
                try(Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress(ipAddress, 22), 100);
                    for (CredentialsRest credential : credentialsList) {
                        logger.debug("trying to connect using ->{}",credential.getUsername());
                        if(fetch(ipAddress,credential.getUsername(), credential.getPassword())){
                            logger.debug("fetch completed using ->{}",credential.getUsername());
                            break;
                        }
                    }
                } catch (Exception e) {
                    // Ignore exceptions, as we only care about successful connections
                }
            }
        }
    }
    private void performScanOnCredentialSpecificSetOfIp(NetworkScanRest networkScanRest){
        List<String> IP_ADDRESS_LIST = networkScanRest.getIpList();
        List<Long> credentialsIds = networkScanRest.getRefIds();
        List<CredentialsRest> credentialsList = credentialsIds.stream().map(credentialsService::findById).toList();
        for(String ip: IP_ADDRESS_LIST) {
            for (CredentialsRest credential : credentialsList) {
                logger.debug("trying to connect using ->{}",credential.getUsername());
                if(fetch(ip,credential.getUsername(), credential.getPassword())){
                    logger.debug("fetch completed using ->{}",credential.getUsername());
                    break;
                }
            }
        }

    }
    private boolean fetch(String ipAddress, String username, String password )  {
        try{

            logger.debug("Scanning -> {} " , ipAddress);
            new LinuxCommandExecutorManager(ipAddress, username, password,22).fetch();
            persistToDB.saveToDB();
            return true;
        }catch (AssetDiscoveryApiException e){
            logger.error("Network Scanner Error for host-> {}" ,ipAddress );
            return false;
        }

    }


}
