package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Asset;
import com.serviceops.assetdiscovery.entity.ComputerSystem;
import com.serviceops.assetdiscovery.entity.Monitor;
import com.serviceops.assetdiscovery.rest.AssetRest;
import com.serviceops.assetdiscovery.rest.ComputerSystemRest;
import com.serviceops.assetdiscovery.rest.MonitorRest;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

import java.util.LinkedHashMap;

public class MonitorOps extends AssetBaseOps<Monitor,MonitorRest> {

    private static MonitorRest monitorRest;
    private static Monitor monitor;
    public MonitorOps(Monitor monitor,MonitorRest monitorRest) {
        super(monitor,monitorRest);
        this.monitor = monitor;
        this.monitorRest = monitorRest;
    }

    public MonitorRest entityToRest(){
        super.entityToRest(monitor);
        monitorRest.setMonitorType(monitor.getMonitorType());
        monitorRest.setDescription(monitor.getDescription());
        monitorRest.setInstalledDate(monitor.getInstalledDate());
        monitorRest.setSize(monitor.getSize());
        monitorRest.setWeekOfManufacture(monitor.getWeekOfManufacture());
        monitorRest.setYearOfManufacture(monitor.getYearOfManufacture());
        monitorRest.setScreenWidth(monitor.getScreenWidth());
        monitorRest.setScreenHeight(monitor.getScreenHeight());
        monitorRest.setPnpDeviceId(monitor.getPnpDeviceId());
        return monitorRest;
    }
    public Monitor restToEntity(){
        super.restToEntity(monitorRest);
        monitor.setMonitorType(monitorRest.getMonitorType());
        monitor.setDescription(monitorRest.getDescription());
        monitor.setInstalledDate(monitorRest.getInstalledDate());
        monitor.setSize(monitorRest.getSize());
        monitor.setWeekOfManufacture(monitorRest.getWeekOfManufacture());
        monitor.setYearOfManufacture(monitorRest.getYearOfManufacture());
        monitor.setScreenWidth(monitorRest.getScreenWidth());
        monitor.setScreenHeight(monitorRest.getScreenHeight());
        monitor.setPnpDeviceId(monitorRest.getPnpDeviceId());
        return monitor;
    }
}
