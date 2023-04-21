package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.HardwarePropertiesRest;

import java.util.List;

public interface HardwarePropertiesService {
    List<HardwarePropertiesRest> findByRefId(long refId);
}
