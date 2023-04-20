package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Monitor;
import com.serviceops.assetdiscovery.rest.MonitorRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

public class MonitorOps extends AssetBaseOps<Monitor, MonitorRest> {

    private final MonitorRest monitorRest;
    private final Monitor monitor;

    public MonitorOps(Monitor monitor, MonitorRest monitorRest) {
        super(monitor, monitorRest);
        this.monitor = monitor;
        this.monitorRest = monitorRest;
    }

    public MonitorRest entityToRest() {
        super.entityToRest(monitor);
        monitorRest.setDescription(monitor.getDescription());
        monitorRest.setScreenWidth(monitor.getScreenWidth());
        monitorRest.setScreenHeight(monitor.getScreenHeight());
        return monitorRest;
    }

    public Monitor restToEntity() {
        super.restToEntity(monitorRest);
        monitor.setDescription(monitorRest.getDescription());
        monitor.setScreenWidth(monitorRest.getScreenWidth());
        monitor.setScreenHeight(monitorRest.getScreenHeight());
        return monitor;
    }
}
