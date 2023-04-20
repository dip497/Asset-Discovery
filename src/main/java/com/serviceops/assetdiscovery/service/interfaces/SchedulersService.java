package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.SchedulerRest;

import java.util.List;

public interface SchedulersService {

    void save(Long networkScanId, SchedulerRest schedulerRest);

    void update(Long networkScanId, Long id, SchedulerRest schedulerRest);

    SchedulerRest findByNetworkScanId(Long networkScanId);

    List<SchedulerRest> findAll();
}
