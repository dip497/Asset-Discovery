package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Credentials;
import com.serviceops.assetdiscovery.exception.ResourceAlreadyExistsException;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.service.interfaces.CredentialsService;
import com.serviceops.assetdiscovery.utils.mapper.CredentialsOps;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CredentialsServiceImpl implements CredentialsService {
    private CustomRepository customRepository;
    private CredentialsOps credentialsOps;

    public CredentialsServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
    }

    @Override
    public void save(CredentialsRest credentialsRest) {
        if (customRepository.findByColumn("ipAddress", credentialsRest.getIpAddress(), Credentials.class).isPresent()){
            throw new ResourceAlreadyExistsException(this.getClass().getSimpleName(),"ipaddress",credentialsRest.getIpAddress());

        }else{
            credentialsOps = new CredentialsOps();
            customRepository.save(credentialsOps.restToEntity(credentialsRest));
        }
        //new ResourceNotFoundException(this.getClass().getSimpleName(),"ipaddress",credentialsRest.getIpAddress()));
        //new ResourceAlreadyExistsException(this.getClass().getSimpleName(),"ipaddress",credentialsRest.getIpAddress()));


    }

    @Override
    public List<CredentialsRest> findAll() {
        List<Credentials> credential = customRepository.findAll(Credentials.class);
        credential.stream().forEach(System.out::println);
        return credential.stream().map(c -> new CredentialsOps().entityToRest(new Credentials())).toList();
    }

    @Override
    public CredentialsRest findByIpAddress(String inet4Address) {
        Credentials credentials = customRepository.findByColumn("ipAddress", inet4Address.toString(), Credentials.class).get();
        return new CredentialsOps().entityToRest(credentials);
    }

    @Override
    public CredentialsRest findById(Long id) {
        return new CredentialsOps().entityToRest(customRepository.findByColumn("id", id.toString(), Credentials.class).get());
    }
}
