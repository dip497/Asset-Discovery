package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.CredentialsRest;

import java.util.List;

public interface CredentialsService {
    CredentialsRest save(CredentialsRest credentialsRest);

    CredentialsRest findById(long id);

    List<CredentialsRest> findAll();

    void update(long id, CredentialsRest credentialsRest);

    void deleteById(long id);
}
