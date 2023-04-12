package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.CredentialsRest;

import java.net.Inet4Address;
import java.util.List;
import java.util.Map;

public interface CredentialsService {
    CredentialsRest save(CredentialsRest credentialsRest);
    List<CredentialsRest> findAll();
    CredentialsRest findByIpAddress(String inet4Address);
    CredentialsRest findById(Long id);

    void deleteById(Long id);

    void update(Long id, Map<String, Object> fields);
}
