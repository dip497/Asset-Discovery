package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Credentials;
import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CredentialsOps extends SingleBaseOps<Credentials, CredentialsRest> {
    private final Credentials credentials;
    private final CredentialsRest credentialsRest;
    private PasswordEncoder passwordEncoder;

    public CredentialsOps(Credentials credentials, CredentialsRest credentialsRest) {
        super(credentials, credentialsRest);
        this.credentials = credentials;
        this.credentialsRest = credentialsRest;
    }

    public CredentialsRest entityToRest() {
        super.entityToRest(credentials);
        credentialsRest.setUsername(credentials.getUsername());
        credentialsRest.setPassword(credentials.getPassword());
        credentialsRest.setDescription(credentials.getDescription());
        credentialsRest.setCredentialType(credentials.getCredentialType());
        return credentialsRest;
    }

    public Credentials restToEntity() {
        super.restToEntity(credentialsRest);
        credentials.setUsername(credentialsRest.getUsername());
        credentials.setPassword(credentialsRest.getPassword());
        credentials.setDescription(credentialsRest.getDescription());
        credentials.setCredentialType(credentialsRest.getCredentialType());
        return credentials;
    }
}
