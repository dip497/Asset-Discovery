package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.CredentialsRest;

import java.util.List;
import java.util.Map;

public interface CredentialsService {
    boolean testConnection(Map<String, String> parameters);

    CredentialsRest save(CredentialsRest credentialsRest);

    CredentialsRest findById(long id);

    List<CredentialsRest> findAll();

    CredentialsRest update(long id, CredentialsRest credentialsRest);

    boolean deleteById(long id);
}
