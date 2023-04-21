package com.serviceops.assetdiscovery.service.interfaces;


import com.serviceops.assetdiscovery.rest.PointingDeviceRest;

import java.util.List;

public interface PointingDeviceService {
    void save(long id);

    List<PointingDeviceRest> findAllByRefId(long id);

    PointingDeviceRest updateById(long refId, long id, PointingDeviceRest pointingDeviceRest);

    boolean deleteById(long refId, long id);
}
