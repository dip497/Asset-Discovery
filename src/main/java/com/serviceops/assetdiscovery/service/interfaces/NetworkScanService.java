package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.NetworkScanRest;

import java.util.List;

public interface NetworkScanService {
    void scan(long id);

    void save(NetworkScanRest networkScanRest);

    NetworkScanRest findById(long id);

    List<NetworkScanRest> findAll();

    void updateById(long id, NetworkScanRest networkScanRest);

    void deleteById(long id);

}
