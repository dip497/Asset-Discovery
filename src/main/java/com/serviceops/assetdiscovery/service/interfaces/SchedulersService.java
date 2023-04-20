package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.SchedulerRest;

import java.util.List;

public interface SchedulersService {

    void save(long networkScanId, SchedulerRest schedulerRest);

    void update(long networkScanId, long id, SchedulerRest schedulerRest);

    SchedulerRest findByNetworkScanId(long networkScanId);

    List<SchedulerRest> findAll();

    void deleteByNetworkScanId(long id);
}
