package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.MonitorRest;

import java.util.List;

public interface MonitorService {

    void save(Long id);
    void update(Long refId, Long id, MonitorRest monitorRest);
    void deleteById(Long refId, Long id);
    List<MonitorRest> getMonitors(Long id);


}
