package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.CredentialsRest;

import java.net.Inet4Address;
import java.util.List;

public interface CredentialsService {
    void save(CredentialsRest credentialsRest);
    List<CredentialsRest> findAll();
    CredentialsRest findByIpAddress(Inet4Address inet4Address);
}
