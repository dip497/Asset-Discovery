package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.ProcessorRest;

import java.util.List;

public interface ProcessorService {
    void save(Long refId);

    void update(Long refId, ProcessorRest processorRest);

    void deleteById(Long refId);

    List<ProcessorRest> findByRefId(Long refId);
}
