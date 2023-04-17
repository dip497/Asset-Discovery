package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.MonitorRest;

import java.util.List;

public interface MonitorService {

    void save(Long id);
    void update(Long refId, Long id, MonitorRest monitorRest);
    void deleteById(Long id, Long refId);
    List<MonitorRest> getMonitors(Long id);


}
