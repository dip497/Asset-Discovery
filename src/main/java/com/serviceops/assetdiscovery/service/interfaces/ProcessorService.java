package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.PointingDeviceRest;
import com.serviceops.assetdiscovery.rest.ProcessorRest;

public interface ProcessorService {
    public void save(Long id);
    void update(Long id, ProcessorRest processorRest);
    void deleteById(Long id);
    public void findByRefId(Long id);
}
