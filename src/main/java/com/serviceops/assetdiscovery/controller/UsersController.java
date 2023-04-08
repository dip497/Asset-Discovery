package com.serviceops.assetdiscovery.controller;

import com.serviceops.assetdiscovery.rest.UsersRest;
import com.serviceops.assetdiscovery.service.interfaces.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }
   @PostMapping("/save")
   public ResponseEntity<UsersRest> createUser(@Valid @RequestBody UsersRest usersRest){
        return new ResponseEntity<>(usersService.save(usersRest), HttpStatus.CREATED);
        //hello
   }
}
