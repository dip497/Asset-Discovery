package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.MonitorRest;

import java.util.List;

public interface MonitorService {

    void save(Long id);
    void update(MonitorRest monitorRest);
    void deleteById(Long id);
    List<MonitorRest> get(Long id);


}
