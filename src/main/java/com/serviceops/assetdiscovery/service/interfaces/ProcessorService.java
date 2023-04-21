package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.ProcessorRest;

import java.util.List;

public interface ProcessorService {
    void save(long refId);

    List<ProcessorRest> findByRefId(long refId);

    ProcessorRest updateByRefId(long refId, ProcessorRest processorRest);

    boolean deleteById(long refId);
}
