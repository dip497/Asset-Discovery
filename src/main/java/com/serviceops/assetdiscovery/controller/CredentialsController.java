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
        logger.debug("Creating Credentials with username -> {}",credentialsRest.getUsername());
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

    @DeleteMapping("/{id}")
    public void deleteCredentialsById(@PathVariable Long id){

        logger.debug("Deleting Credentials with id -> {}",id);

        credentialsService.deleteById(id);

    }

    @PutMapping("/{id}")
    public void updateCredential(@PathVariable("id") Long id,@RequestBody CredentialsRest credentialsRest){

        logger.debug("Updating Credentials with id -> {}",id);

        credentialsService.update(id,credentialsRest);

    }

}
