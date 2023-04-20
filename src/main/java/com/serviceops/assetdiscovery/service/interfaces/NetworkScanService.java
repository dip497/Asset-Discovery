package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.NetworkScanRest;

import java.util.List;

public interface NetworkScanService {
    NetworkScanRest findById(Long id);

    void save(NetworkScanRest networkScanRest);

    List<NetworkScanRest> findAll();

    void deleteById(Long id);

    void update(Long id, NetworkScanRest networkScanRest);

    void scan(Long id);

}
