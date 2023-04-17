package com.serviceops.assetdiscovery.config;

import com.serviceops.assetdiscovery.entity.Credentials;
import com.serviceops.assetdiscovery.exception.AssetDiscoveryApiException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.repository.PersistToDB;
import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.rest.NetworkScanRest;
import com.serviceops.assetdiscovery.service.interfaces.CredentialsService;
import com.serviceops.assetdiscovery.service.interfaces.NetworkScanService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NetworkScanner {
    private final NetworkScanService networkScanService;
    private final Logger logger = LoggerFactory.getLogger(NetworkScanner.class);
    private final CustomRepository customRepository;
    private final CredentialsService credentialsService;
    private final PersistToDB persistToDB;

    public NetworkScanner(NetworkScanService networkScanService, CustomRepository customRepository, CredentialsService credentialsService, PersistToDB persistToDB) {
        this.networkScanService = networkScanService;
        this.customRepository = customRepository;
        this.credentialsService = credentialsService;
        this.persistToDB = persistToDB;
    }
    @Async
    public void scanNetworkRange(NetworkScanRest networkScanRest) {
        switch (networkScanRest.getIpRangeType()) {
            case ENTIRE_NETWORK -> performScanOnCredentialEntireNetwork();
            case SPECIFIC_SET_OF_IP -> performScanOnCredentialSpecificSetOfIp(networkScanRest);
        }
        networkScanRest.setLastScan(LocalDateTime.now());
        networkScanService.update(networkScanRest.getId(), networkScanRest);
    }

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
