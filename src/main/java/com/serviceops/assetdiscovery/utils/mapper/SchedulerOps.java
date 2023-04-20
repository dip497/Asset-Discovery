package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Schedulers;
import com.serviceops.assetdiscovery.rest.SchedulerRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class SchedulerOps extends SingleBaseOps<Schedulers, SchedulerRest> {
    private final Schedulers schedulers;
    private final SchedulerRest schedulerRest;
    public SchedulerOps(Schedulers schedulers, SchedulerRest schedulerRest) {
        super(schedulers, schedulerRest);
        this.schedulers = schedulers;
        this.schedulerRest =schedulerRest;
    }

    public SchedulerRest entityToRest() {
        super.entityToRest(schedulers);
        schedulerRest.setNetworkScanRestId(schedulers.getNetworkScanId());
        schedulerRest.setScanType(schedulers.getScanType());
        schedulerRest.setTime(schedulers.getTime());
        schedulerRest.setDate(schedulers.getDate());
        schedulerRest.setMonth(schedulers.getMonth());
        schedulerRest.setInterval(schedulers.getInterval());
        schedulerRest.setId(schedulers.getId());
        schedulerRest.setStartTime(schedulers.getStartTime());
        schedulerRest.setWeek(schedulers.getWeek());
        return schedulerRest;


    }

    public Schedulers restToEntity() {
        super.restToEntity(schedulerRest);
        schedulers.setNetworkScanId(schedulerRest.getNetworkScanRestId());
        schedulers.setScanType(schedulerRest.getScanType());
        schedulers.setTime(schedulerRest.getTime());
        schedulers.setDate(schedulerRest.getDate());
        schedulers.setMonth(schedulerRest.getMonth());
        schedulers.setInterval(schedulerRest.getInterval());
        schedulers.setId(schedulerRest.getId());
        schedulers.setStartTime(schedulerRest.getStartTime());
        schedulers.setWeek(schedulerRest.getWeek());
        return schedulers;
    }
}
