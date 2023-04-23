package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.SchedulerRest;

import java.util.List;

public interface SchedulersService {

    SchedulerRest save(long networkScanId, SchedulerRest schedulerRest);

    SchedulerRest update(long networkScanId, SchedulerRest schedulerRest);

    SchedulerRest findByNetworkScanId(long networkScanId);

    List<SchedulerRest> findAll();

    boolean deleteByNetworkScanId(long id);
}
