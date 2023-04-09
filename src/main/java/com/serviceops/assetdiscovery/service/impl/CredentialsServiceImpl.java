package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Credentials;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.service.interfaces.CredentialsService;
import com.serviceops.assetdiscovery.utils.mapper.CredentialsOps;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;
@Service
public class CredentialsServiceImpl implements CredentialsService {
    private CustomRepository customRepository;
    private CredentialsOps credentialsOps;

    public CredentialsServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
    }

    @Override
    public void save(CredentialsRest credentialsRest) {
        Credentials credential = new Credentials();
        credentialsOps = new CredentialsOps(credential, credentialsRest);
        customRepository.save(credentialsOps.restToEntity(credentialsRest));
    }

    @Override
    public List<CredentialsRest> findAll() {
        List<Credentials> credentils = customRepository.findAll(Credentials.class);
        return credentils.stream().map(c -> new CredentialsOps(c ,new CredentialsRest()).entityToRest(c)).toList();
    }

    @Override
    public CredentialsRest findByIpAddress(Inet4Address inet4Address) {

        return new CredentialsRest();
    }
}
