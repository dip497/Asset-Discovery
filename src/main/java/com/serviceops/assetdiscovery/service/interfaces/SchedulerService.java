package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.entity.Scheduler;
import com.serviceops.assetdiscovery.rest.SchedulerRest;

import java.util.List;

public interface SchedulerService {
    void save(SchedulerRest schedulerRest);
    SchedulerRest findById(Long id);

    List<SchedulerRest> findAll();
}
