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
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.NetworkScanOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
            customRepository.update(new NetworkScanOps(new NetworkScan(),networkScanRest).restToEntity());
        }else{
            networkScanRest.setEnabled(true);
            customRepository.save(new NetworkScanOps(new NetworkScan(),networkScanRest).restToEntity());

        }

    }
    @Override
    public void saveOfSetOfIp(NetworkScanRest networkScanRest, List<String> ipAddress){
        /*StringBuilder stringBuilder = new StringBuilder();
        ipAddress.forEach(e -> stringBuilder.append(e).append(","));
        networkScanRest.setRefIds(stringBuilder.toString());*/
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
            customRepository.save(new NetworkScanOps(networkScan.get(),networkScanRest).restToEntity());
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
                    performScanOnCredentialEntireNetwork();


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

    @Override
    public Scheduler getSchedulerById(Long schedulerRefId) {
        return schedulerService.findById(schedulerRefId);
    }

/*    public Scheduler findSchedulerByRefId(Long id){
        Optional<NetworkScan> networkScan = customRepository.findByColumn("schedulerRefId", id, NetworkScan.class);
        return networkScan.get();
    }*/

    private void performScanOnCredentialEntireNetwork(){
        List<Credentials> credentials = customRepository.findAll(Credentials.class);
        credentials.forEach(c -> fetch(c.getIpAddress(), c.getUsername(), c.getPassword()));
    }
    private void performScanOnCredentialSpecificSetOfIp(NetworkScanRest networkScanRest){
        List<CredentialsRest> list = networkScanRest.getRefIds().stream().map(c -> credentialsService.findById(Long.valueOf(c))).toList();

        list.forEach(
                credentialsRest ->
                        fetch(credentialsRest.getIpAddress(),
                                credentialsRest.getUsername(),
                                credentialsRest.getPassword()));
    }
    private void fetch(String ipAddress, String username, String password )  {
        try{

            logger.debug("Scanning -> {} " , ipAddress);
            new LinuxCommandExecutorManager(ipAddress, username, password,22).fetch();
            persistToDB.saveToDB();
        }catch (AssetDiscoveryApiException e){
            logger.error("Network Scanner Error for host-> {}" ,ipAddress );
        }

    }


}
