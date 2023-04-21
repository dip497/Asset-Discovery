package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Monitor;
import com.serviceops.assetdiscovery.rest.MonitorRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class MonitorOps extends SingleBaseOps<Monitor, MonitorRest> {

    @Override
    public MonitorRest entityToRest(Monitor monitor, MonitorRest monitorRest) {
        super.entityToRest(monitor, monitorRest);
        monitorRest.setRefId(monitor.getRefId());
        monitorRest.setManufacturer(monitor.getManufacturer());
        monitorRest.setDescription(monitor.getDescription());
        return monitorRest;
    }

    @Override
    public Monitor restToEntity(Monitor monitor, MonitorRest monitorRest) {
        super.restToEntity(monitor, monitorRest);
        monitor.setRefId(monitorRest.getRefId());
        monitor.setManufacturer(monitorRest.getManufacturer());
        monitor.setDescription(monitorRest.getDescription());
        return monitor;
    }
}
