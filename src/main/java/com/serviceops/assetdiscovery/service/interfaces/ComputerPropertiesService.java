package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.ComputerPropertiesRest;

public interface ComputerPropertiesService {

    ComputerPropertiesRest findByRefId(Long refId);

    void update(Long refId,ComputerPropertiesRest computerPropertiesRest);

}
