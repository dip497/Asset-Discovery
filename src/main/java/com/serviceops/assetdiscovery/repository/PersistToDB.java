package com.serviceops.assetdiscovery.repository;

import com.serviceops.assetdiscovery.exception.AssetDiscoveryApiException;
import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.service.interfaces.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PersistToDB {
    private final AssetService assetService;
    private final MotherBoardService motherBoardService;
    private final PhysicalDiskService physicalDiskService;
    private final ComputerSystemService computerSystemService;
    private final KeyboardService keyboardService;
    private final BiosService biosService;
    private final RamService ramService;
    private final MonitorService monitorService;
    private final OsService osService;
    private final NetworkAdapterService networkAdapterService;
    private final PointingDeviceService pointingDeviceService;
    private final LogicalDiskService logicalDiskService;
    private final ProcessorService processorService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public PersistToDB(AssetService assetService, MotherBoardService motherBoardService,
            PhysicalDiskService physicalDiskService, ComputerSystemService computerSystemService,
            KeyboardService keyboardService, BiosService biosService, RamService ramService,
            MonitorService monitorService, OsService osService, NetworkAdapterService networkAdapterService,
            PointingDeviceService pointingDeviceService, LogicalDiskService logicalDiskService,
            ProcessorService processorService) {
        this.assetService = assetService;
        this.motherBoardService = motherBoardService;
        this.physicalDiskService = physicalDiskService;
        this.computerSystemService = computerSystemService;
        this.keyboardService = keyboardService;
        this.biosService = biosService;
        this.ramService = ramService;
        this.monitorService = monitorService;
        this.osService = osService;
        this.networkAdapterService = networkAdapterService;
        this.pointingDeviceService = pointingDeviceService;
        this.logicalDiskService = logicalDiskService;
        this.processorService = processorService;
    }

    @Transactional
    public void saveToDB() {
        try {
            AssetRest assetRest = assetService.save();
            logger.info("Saving to db -> {}", assetRest.getIpAddress());
            logicalDiskService.save(assetRest.getId());
            motherBoardService.save(assetRest.getId());
            physicalDiskService.save(assetRest.getId());
            computerSystemService.save(assetRest.getId());
            keyboardService.save(assetRest.getId());
            biosService.save(assetRest);
            networkAdapterService.save(assetRest.getId());
            ramService.save(assetRest.getId());
            monitorService.save(assetRest.getId());
            osService.save(assetRest.getId());
            pointingDeviceService.save(assetRest.getId());
            processorService.save(assetRest.getId());
            logger.info("saved to db -> {}", assetRest.getIpAddress());
        } catch (Exception e) {
            logger.error("fail to save");
            throw new AssetDiscoveryApiException("Error while Persisting asset");
        }
    }

}
