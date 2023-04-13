package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.HardwarePropertiesRest;

public interface HardwarePropertiesService {

    HardwarePropertiesRest findByRefId(Long refId);

}
