package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Monitor;
import com.serviceops.assetdiscovery.rest.MonitorRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class MonitorOps extends SingleBaseOps<Monitor,MonitorRest> {

    private final MonitorRest monitorRest;
    private final Monitor monitor;

    public MonitorOps(Monitor monitor, MonitorRest monitorRest) {
        super(monitor, monitorRest);
        this.monitor = monitor;
        this.monitorRest = monitorRest;
    }

    public MonitorRest entityToRest() {
        super.entityToRest(monitor);
        monitorRest.setRefId(monitor.getRefId());
        monitorRest.setManufacturer(monitor.getManufacturer());
        monitorRest.setDescription(monitor.getDescription());
        return monitorRest;
    }

    public Monitor restToEntity() {
        super.restToEntity(monitorRest);
        monitor.setRefId(monitorRest.getRefId());
        monitor.setManufacturer(monitorRest.getManufacturer());
        monitor.setDescription(monitorRest.getDescription());
        return monitor;
    }
}
