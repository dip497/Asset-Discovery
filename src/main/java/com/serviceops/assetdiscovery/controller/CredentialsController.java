package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.service.interfaces.CredentialsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CredentialsController {
    private final CredentialsService credentialsService;

    public CredentialsController(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }
    @PostMapping("/credentials")
    public void  addCredentials(@RequestBody CredentialsRest credentialsRest){
        credentialsService.save(credentialsRest);
    }

    @GetMapping("/credentials/{id}")
    public CredentialsRest getCredential(@PathVariable Long id){
        return credentialsService.findById(id);
    }
    @GetMapping("/credentials")
    public List<CredentialsRest> getAllCredentials(){
        return credentialsService.findAll();
    }



}
