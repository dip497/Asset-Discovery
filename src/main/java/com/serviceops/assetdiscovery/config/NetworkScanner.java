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

import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
            case ENTIRE_NETWORK -> performScanOnCredentialEntireNetwork(networkScanRest);
            case SPECIFIC_SET_OF_IP -> performScanOnCredentialSpecificSetOfIp(networkScanRest);
        }
        networkScanRest.setLastScan(LocalDateTime.now());
        networkScanService.update(networkScanRest.getId(), networkScanRest);
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
