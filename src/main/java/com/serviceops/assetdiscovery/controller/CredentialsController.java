package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.service.interfaces.CredentialsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/credentials")
public class CredentialsController {
    private final CredentialsService credentialsService;
    private final Logger logger = LoggerFactory.getLogger(CredentialsController.class);

    public CredentialsController(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @PostMapping()
    public ResponseEntity<CredentialsRest> addCredentials(@RequestBody CredentialsRest credentialsRest){
        logger.debug("Creating Credentials with IpAddress -> {}",credentialsRest.getIpAddress());
        return new ResponseEntity<>(credentialsService.save(credentialsRest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public CredentialsRest getCredential(@PathVariable Long id){
        logger.debug("Finding credentials with id ->{}", id);
        return credentialsService.findById(id);
    }

    @GetMapping()
    public List<CredentialsRest> getAllCredentials(){
        logger.debug("Finding all credentials");
        return credentialsService.findAll();
    }


    @PatchMapping("/{id}")
    public void updateCredentials(@PathVariable Long id, @RequestBody Map<String, Object> fields){

        logger.debug("Updating Credentials field -> {}",fields);

        credentialsService.update(id,fields);

    }

    @DeleteMapping("/{id}")
    public void deleteCredentialsById(@PathVariable Long id){

        logger.debug("Deleting Credentials with id -> {}",id);

        credentialsService.deleteById(id);

    }



}
