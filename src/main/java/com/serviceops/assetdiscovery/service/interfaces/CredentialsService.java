package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.CredentialsRest;

import java.util.List;

public interface CredentialsService {
    CredentialsRest save(CredentialsRest credentialsRest);

    List<CredentialsRest> findAll();

    CredentialsRest findById(Long id);

    void deleteById(Long id);

    void update(Long id, CredentialsRest credentialsRest);
}
