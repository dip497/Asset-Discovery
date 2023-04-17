package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.entity.Scheduler;
import com.serviceops.assetdiscovery.rest.SchedulerRest;

public interface SchedulerService {
    Scheduler save(SchedulerRest schedulerRest);
    Scheduler findById(Long id);
}
