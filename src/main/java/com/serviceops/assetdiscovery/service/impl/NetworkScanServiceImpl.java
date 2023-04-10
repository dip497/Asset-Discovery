package com.serviceops.assetdiscovery.service.impl;

import com.jcraft.jsch.JSchException;
import com.serviceops.assetdiscovery.entity.Credentials;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.service.interfaces.*;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class NetworkScanServiceImpl implements NetworkScanService {
    private final CustomRepository customRepository;
    private final AssetService assetService;
    private final MotherBoardService motherBoardService;
    private final PhysicalDiskService physicalDiskService;
    private final ComputerSystemService computerSystemService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public NetworkScanServiceImpl(CustomRepository customRepository, AssetService assetService, MotherBoardService motherBoardService, PhysicalDiskService physicalDiskService, ComputerSystemService computerSystemService) {
        this.customRepository = customRepository;
        this.assetService = assetService;
        this.motherBoardService = motherBoardService;
        this.physicalDiskService = physicalDiskService;
        this.computerSystemService =  computerSystemService;
    }

    @Override
    public void scan() {
        List<Credentials> credentials = customRepository.findAll(Credentials.class);
        credentials.forEach(c -> {
            try {
                logger.debug("Scanning -> {} " ,c.getIpAddress());
                new LinuxCommandExecutorManager(c.getIpAddress(),c.getUsername(),c.getPassword(),22).fetch();
                saveToDB();
            } catch (JSchException | IOException e) {
                logger.warn("failed to scan -> {}" ,c.getIpAddress());
                throw new RuntimeException(e.getMessage());
            }
        });
    }
    private void saveToDB(){
        Long refId = saveAsset();
        motherBoardService.save(refId);
        physicalDiskService.save(refId);
        computerSystemService.save(refId);
    }
    private Long  saveAsset(){
        return assetService.save();
    }

}