package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Credentials;
import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class CredentialsOps extends SingleBaseOps<Credentials, CredentialsRest> {
    private static Credentials credentials = new Credentials();
    private static CredentialsRest credentialsRest = new CredentialsRest();
    public CredentialsOps() {
        super(credentials, credentialsRest);
    }

    @Override
    public  CredentialsRest entityToRest(Credentials credentials) {
        super.entityToRest(credentials);
        credentialsRest.setUsername(credentials.getUsername());
        credentialsRest.setPassword(credentials.getPassword());
        credentialsRest.setIpAddress(credentials.getIpAddress());
        return credentialsRest;
    }

    @Override
    public Credentials restToEntity(CredentialsRest credentialsRest) {
        super.restToEntity(credentialsRest);
        credentials.setUsername(credentialsRest.getUsername());
        credentials.setPassword(credentialsRest.getPassword());
        credentials.setIpAddress(credentialsRest.getIpAddress());
        return credentials;
    }
}
