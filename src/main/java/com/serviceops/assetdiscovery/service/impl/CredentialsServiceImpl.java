package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Credentials;
import com.serviceops.assetdiscovery.exception.ComponentNotFoundException;
import com.serviceops.assetdiscovery.exception.ResourceAlreadyExistsException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.service.interfaces.CredentialsService;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import com.serviceops.assetdiscovery.utils.PasswordEncoderSSH;
import com.serviceops.assetdiscovery.utils.mapper.CredentialsOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CredentialsServiceImpl implements CredentialsService {
    private static final Logger logger = LoggerFactory.getLogger(CredentialsServiceImpl.class);
    private final CustomRepository customRepository;
    private final CredentialsOps credentialsOps;

    public CredentialsServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
        credentialsOps = new CredentialsOps();
    }

    @Override
    public boolean testConnection(Map<String, String> parameters) {
        return LinuxCommandExecutorManager.testConnection(parameters.get("ipAddress"),
                parameters.get("username"), parameters.get("password"), 22);
    }

    @Override
    public CredentialsRest save(CredentialsRest credentialsRest) {
        if (customRepository.findByColumn("id", credentialsRest.getId(), Credentials.class).isPresent()) {
            logger.error("Credentials already exists with id -> {} ", credentialsRest.getId());
            throw new ResourceAlreadyExistsException(this.getClass().getSimpleName(), "id",
                    credentialsRest.getUsername());
        } else {
            Credentials credential = new Credentials();
            credentialsRest.setPassword(PasswordEncoderSSH.encryptPassword(credentialsRest.getPassword()));
            customRepository.save(credentialsOps.restToEntity(credential, credentialsRest));
            logger.debug("saved credentials of username -> {} ", credentialsRest.getUsername());
            return credentialsOps.entityToRest(credential, credentialsRest);
        }
    }

    @Override
    public CredentialsRest findById(long id) {
        Optional<Credentials> fetchCredentials = customRepository.findByColumn("id", id, Credentials.class);
        if (fetchCredentials.isPresent()) {
            logger.info("Credential found -> {}", id);
            return credentialsOps.entityToRest(fetchCredentials.get(), new CredentialsRest());

        } else {
            throw new ComponentNotFoundException("Credentials", "id", id);

        }
    }

    @Override
    public List<CredentialsRest> findAll() {
        List<Credentials> credential = customRepository.findAll(Credentials.class);
        logger.info("found all credential");
        return credential.stream().map(c -> credentialsOps.entityToRest(c, new CredentialsRest())).toList();
    }

    @Override
    public void update(long id, CredentialsRest credentialsRest) {
        Optional<Credentials> fetchCredential = customRepository.findByColumn("id", id, Credentials.class);
        if (fetchCredential.isEmpty()) {
            throw new ComponentNotFoundException("Credentials", "id", id);
        } else {
            Credentials credential = fetchCredential.get();
            credentialsRest.setPassword(PasswordEncoderSSH.encryptPassword(credentialsRest.getPassword()));
            credential = credentialsOps.restToEntity(credential, credentialsRest);
            customRepository.save(credential);

            logger.info("Updated credentials with id -> {}", credentialsRest.getId());
        }

    }

    @Override
    public void deleteById(long id) {

        customRepository.deleteById(Credentials.class, id, "id");

        logger.info("Credential deleted with id ->{}", id);
    }
}
