package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Credentials;
import com.serviceops.assetdiscovery.exception.ResourceAlreadyExistsException;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.service.interfaces.CredentialsService;
import com.serviceops.assetdiscovery.utils.mapper.CredentialsOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CredentialsServiceImpl implements CredentialsService {
    private final CustomRepository customRepository;
    private CredentialsOps credentialsOps;
    private final Logger logger = LoggerFactory.getLogger(CredentialsServiceImpl.class);

    public CredentialsServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
    }

    @Override
    public CredentialsRest save(CredentialsRest credentialsRest) {
        logger.info("Getting credential with id {}", credentialsRest.getId());
        if (customRepository.findByColumn("id", credentialsRest.getId(), Credentials.class).isPresent()){
            logger.error("Credentials already exists with id -> {} ",credentialsRest.getId());
            throw new ResourceAlreadyExistsException(this.getClass().getSimpleName(),"id",credentialsRest.getId().toString());
        }else{
            Credentials credential = new Credentials();
            credentialsOps = new CredentialsOps(credential, credentialsRest);
            customRepository.save(credentialsOps.restToEntity());
            logger.debug("saved credentials of ip -> {} ",credentialsRest.getId());
            return new CredentialsOps(credential,credentialsRest).entityToRest();
        }
    }

    @Override
    public List<CredentialsRest> findAll() {
        List<Credentials> credential = customRepository.findAll(Credentials.class);
        logger.info("found all credential");
        return credential.stream().map(c -> new CredentialsOps(c ,new CredentialsRest()).entityToRest()).toList();
    }


    @Override
    public CredentialsRest findById(Long id) {
        Optional<Credentials> fetchCredentials = customRepository.findByColumn("id", id, Credentials.class);
        if(fetchCredentials.isPresent()){
            logger.info("Credential found -> {}", id);
            return new CredentialsOps(fetchCredentials.get(), new CredentialsRest()).entityToRest();

        }else{
            throw new ResourceNotFoundException("Credentials","id",Long.toString(id));

        }
    }

    @Override
    public void deleteById(Long id) {

        customRepository.deleteById(Credentials.class,id,"id");

        logger.info("Credential deleted with id ->{}",id);
    }

    @Override
    public void update(Long id, CredentialsRest credentialsRest) {
        Optional<Credentials> fetchCredential = customRepository.findByColumn("id", id, Credentials.class);
        if(fetchCredential.isEmpty()){
            throw new ResourceNotFoundException("Credentials","id",id.toString());
        }else{

            CredentialsOps credentialsOps = new CredentialsOps(fetchCredential.get(), credentialsRest);

            customRepository.update(credentialsOps.restToEntity());

            logger.info("Updated credentials with id -> {}",credentialsRest.getId());
        }

    }

}
