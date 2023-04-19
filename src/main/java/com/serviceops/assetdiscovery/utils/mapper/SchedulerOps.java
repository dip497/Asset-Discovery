package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Scheduler;
import com.serviceops.assetdiscovery.rest.SchedulerRest;
import com.serviceops.assetdiscovery.utils.mapper.base.BaseOps;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class SchedulerOps extends SingleBaseOps<Scheduler, SchedulerRest> {
    private final Scheduler scheduler;
    private final SchedulerRest schedulerRest;
    public SchedulerOps(Scheduler scheduler, SchedulerRest schedulerRest) {
        super(scheduler, schedulerRest);
        this.scheduler = scheduler;
        this.schedulerRest =schedulerRest;
    }

    public SchedulerRest entityToRest() {
        super.entityToRest(scheduler);
        schedulerRest.setScanType(scheduler.getScanType());
        schedulerRest.setTime(scheduler.getTime());
        schedulerRest.setDate(scheduler.getDate());
        schedulerRest.setMonth(scheduler.getMonth());
        schedulerRest.setInterval(scheduler.getInterval());
        schedulerRest.setId(scheduler.getId());
        schedulerRest.setWeek(scheduler.getWeek());
        return schedulerRest;


    }

    public Scheduler restToEntity() {
        super.restToEntity(schedulerRest);
        scheduler.setScanType(schedulerRest.getScanType());
        scheduler.setTime(schedulerRest.getTime());
        scheduler.setDate(schedulerRest.getDate());
        scheduler.setMonth(schedulerRest.getMonth());
        scheduler.setInterval(schedulerRest.getInterval());
        scheduler.setId(schedulerRest.getId());
        scheduler.setWeek(schedulerRest.getWeek());
        return scheduler;
    }
}
