package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.MonitorRest;

import java.util.List;

public interface MonitorService {

    void save(long id);

    MonitorRest updateById(long refId, long id, MonitorRest monitorRest);

    boolean deleteById(long refId, long id);

    List<MonitorRest> findAllByRefId(long id);


}
