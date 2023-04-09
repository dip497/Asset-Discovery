package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Credentials;
import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class CredentialsOps extends SingleBaseOps<Credentials, CredentialsRest> {
    private Credentials credentials;
    private CredentialsRest credentialsRest;
    public CredentialsOps(Credentials credentials, CredentialsRest credentialsRest) {
        super(credentials, credentialsRest);
        this.credentials = credentials;
        this.credentialsRest = credentialsRest;
    }

    public  CredentialsRest entityToRest() {
        super.entityToRest(this.credentials);
        credentialsRest.setUsername(this.credentials.getUsername());
        credentialsRest.setPassword(this.credentials.getPassword());
        credentialsRest.setIpAddress(this.credentials.getIpAddress());
        return credentialsRest;
    }

    public Credentials restToEntity() {
        super.restToEntity(this.credentialsRest);
        credentials.setUsername(this.credentialsRest.getUsername());
        credentials.setPassword(this.credentialsRest.getPassword());
        credentials.setIpAddress(this.credentialsRest.getIpAddress());
        return credentials;
    }
}
