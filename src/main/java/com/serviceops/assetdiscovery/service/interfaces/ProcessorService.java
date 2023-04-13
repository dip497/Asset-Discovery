package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.ProcessorRest;

import java.util.List;

public interface ProcessorService {
    public void save(Long id);
    void update(Long id, ProcessorRest processorRest);
    void deleteById(Long id);
    public List<ProcessorRest> findByRefId(Long id);
}
