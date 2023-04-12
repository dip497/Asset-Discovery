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
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CredentialsServiceImpl implements CredentialsService {
    private CustomRepository customRepository;
    private CredentialsOps credentialsOps;
    private final Logger logger = LoggerFactory.getLogger(CredentialsServiceImpl.class);

    public CredentialsServiceImpl(CustomRepository customRepository) {
        this.customRepository = customRepository;
    }

    @Override
    public CredentialsRest save(CredentialsRest credentialsRest) {
        if (customRepository.findByColumn("ipAddress", credentialsRest.getIpAddress(), Credentials.class).isPresent()){
            logger.error("Credentials already exists with Ipaddress -> {} ",credentialsRest.getIpAddress());
            throw new ResourceAlreadyExistsException(this.getClass().getSimpleName(),"ipaddress",credentialsRest.getIpAddress());
        }else{
            Credentials credential = new Credentials();
            credentialsOps = new CredentialsOps(credential, credentialsRest);
            customRepository.save(credentialsOps.restToEntity());
            logger.debug("saved credentials of IpAddress -> {} ",credentialsRest.getIpAddress());
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
    public CredentialsRest findByIpAddress(String inet4Address) {
        Optional<Credentials> fetchCredentials = customRepository.findByColumn("ipAddress", inet4Address, Credentials.class);
        if(fetchCredentials.isPresent()){
            logger.info("Credential found for IpAddress -> {}", inet4Address);
            return new CredentialsOps(fetchCredentials.get(), new CredentialsRest()).entityToRest();

        }else{
            throw new ResourceNotFoundException("Credentials","inet4Address",inet4Address);

        }
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
    public void update(Long id, Map<String, Object> fields) {

        CredentialsRest credentialsRest = findById(id);

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findRequiredField(CredentialsRest.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, credentialsRest, value);
        });

        CredentialsOps credentialsOps = new CredentialsOps(new Credentials(),credentialsRest);

        customRepository.save(credentialsOps.restToEntity());

        logger.info("Updated credentials field -> {} for id {}",fields,id);

    }

}
