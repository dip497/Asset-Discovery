package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Credentials;
import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class CredentialsOps extends SingleBaseOps<Credentials, CredentialsRest> {

    @Override
    public CredentialsRest entityToRest(Credentials credentials, CredentialsRest credentialsRest) {
        super.entityToRest(credentials, credentialsRest);
        credentialsRest.setUsername(credentials.getUsername());
        credentialsRest.setPassword(credentials.getPassword());
        credentialsRest.setDescription(credentials.getDescription());
        credentialsRest.setCredentialType(credentials.getCredentialType());
        return credentialsRest;
    }

    @Override
    public Credentials restToEntity(Credentials credentials, CredentialsRest credentialsRest) {
        super.restToEntity(credentials, credentialsRest);
        credentials.setUsername(credentialsRest.getUsername());
        credentials.setPassword(credentialsRest.getPassword());
        credentials.setDescription(credentialsRest.getDescription());
        credentials.setCredentialType(credentialsRest.getCredentialType());
        return credentials;
    }
}
