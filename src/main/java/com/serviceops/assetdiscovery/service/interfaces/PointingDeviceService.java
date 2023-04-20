package com.serviceops.assetdiscovery.service.interfaces;


import com.serviceops.assetdiscovery.rest.PointingDeviceRest;

import java.util.List;

public interface PointingDeviceService {
    void save(Long id);

    void update(Long id, PointingDeviceRest pointingDeviceRest);

    void deleteById(Long id);

    List<PointingDeviceRest> getPointingDevices(Long id);
}
