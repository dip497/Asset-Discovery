package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.MonitorRest;

import java.util.List;

public interface MonitorService {

    void save(long id);

    MonitorRest update(long refId, long id, MonitorRest monitorRest);

    void deleteById(long refId, long id);

    List<MonitorRest> getMonitors(long id);


}
