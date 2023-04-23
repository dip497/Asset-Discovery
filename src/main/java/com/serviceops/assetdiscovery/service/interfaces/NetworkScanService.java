package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.NetworkScanRest;

import java.util.List;

public interface NetworkScanService {
    void scan(long id);

    NetworkScanRest save(NetworkScanRest networkScanRest);

    NetworkScanRest findById(long id);

    List<NetworkScanRest> findAll();

    NetworkScanRest updateById(long id, NetworkScanRest networkScanRest);

    void deleteById(long id);

}
