package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.entity.Scheduler;
import com.serviceops.assetdiscovery.rest.NetworkScanRest;
import com.serviceops.assetdiscovery.rest.SchedulerRest;

import java.util.List;

public interface NetworkScanService {
    NetworkScanRest findById(Long id);
    void save(NetworkScanRest networkScanRest);

    void saveOfSetOfIp(NetworkScanRest networkScanRest, List<String> ipAddress);

    List<NetworkScanRest> findAll();
    void deleteById(Long id);
    void update(Long id,NetworkScanRest networkScanRest);

    void scan(Long id);

    void addScheduler(Long id, SchedulerRest schedulerRest);

    Scheduler getSchedulerById(Long schedulerRefId);
}
