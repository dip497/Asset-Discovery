package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.enums.Role;
import com.serviceops.assetdiscovery.entity.Users;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.AuthenticationRequest;
import com.serviceops.assetdiscovery.rest.AuthenticationResponse;
import com.serviceops.assetdiscovery.rest.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final CustomRepository repository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(CustomRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(
            RegisterRequest request
    ) {
        var user = new Users(request.getName(), request.getEmail(), request.getPassword(), Role.USER);

        repository.save(user);

        var jwtToken = jwtService.
                generateToken(user);
        return new AuthenticationResponse(jwtToken, user.getEmail(), user.getName());

    }

    public AuthenticationResponse authenticate(
            AuthenticationRequest request
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository
                .findByColumn("email",request.getEmail(), Users.class)
                .orElseThrow();

        var jwtToken = jwtService
                .generateToken(user);

        return new AuthenticationResponse(jwtToken, user.getEmail(), user.getName());
    }
}
