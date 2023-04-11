package com.serviceops.assetdiscovery.service.impl;

import com.jcraft.jsch.JSchException;
import com.serviceops.assetdiscovery.entity.Credentials;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.AssetRest;
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
    private final KeyboardService keyboardService;
    private final BiosService biosService;
    private final PointingDeviceService pointingDeviceService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public NetworkScanServiceImpl(CustomRepository customRepository, AssetService assetService, MotherBoardService motherBoardService, PhysicalDiskService physicalDiskService, ComputerSystemService computerSystemService, KeyboardService keyboardService, BiosService biosService, PointingDeviceService pointingDeviceService) {
        this.customRepository = customRepository;
        this.assetService = assetService;
        this.motherBoardService = motherBoardService;
        this.physicalDiskService = physicalDiskService;
        this.computerSystemService =  computerSystemService;
        this.keyboardService = keyboardService;
        this.biosService = biosService;
        this.pointingDeviceService = pointingDeviceService;
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
        AssetRest assetRest = saveAsset();
        motherBoardService.save(assetRest.getId());
        physicalDiskService.save(assetRest.getId());
        computerSystemService.save(assetRest.getId());
        keyboardService.save(assetRest.getId());
        biosService.save(assetRest);
        pointingDeviceService.save(assetRest.getId());
    }
    private AssetRest  saveAsset(){
        return assetService.save();
    }

}
