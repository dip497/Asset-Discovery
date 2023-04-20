package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.ProcessorRest;

import java.util.List;

public interface ProcessorService {
    void save(long refId);

    ProcessorRest update(long refId, ProcessorRest processorRest);

    void deleteById(long refId);

    List<ProcessorRest> findByRefId(long refId);
}
