package com.serviceops.assetdiscovery.service.interfaces;


import com.serviceops.assetdiscovery.rest.PointingDeviceRest;

import java.util.List;

public interface PointingDeviceService {
    void save(long id);

    PointingDeviceRest update(long id, PointingDeviceRest pointingDeviceRest);

    void deleteById(long id);

    List<PointingDeviceRest> getPointingDevices(long id);
}
