package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Users;
import com.serviceops.assetdiscovery.entity.enums.Role;
import com.serviceops.assetdiscovery.exception.ResourceAlreadyExistsException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.AuthenticationRequest;
import com.serviceops.assetdiscovery.rest.AuthenticationResponse;
import com.serviceops.assetdiscovery.rest.RegisterRequest;
import com.serviceops.assetdiscovery.service.interfaces.UsersService;
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
    private final UsersService usersService;

    public AuthenticationService(CustomRepository repository, PasswordEncoder passwordEncoder,
            JwtService jwtService, AuthenticationManager authenticationManager, UsersService usersService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.usersService = usersService;
    }

    public AuthenticationResponse register(RegisterRequest request) {

        var user = new Users(request.getName(), request.getEmail(),
                passwordEncoder.encode(request.getPassword()), Role.USER);

        if (!usersService.checkForUserInDB(user.getEmail())) {

            repository.save(user);

            var jwtToken = jwtService.generateToken(user);
            return new AuthenticationResponse(jwtToken, user.getEmail(), user.getName());

        } else
            throw new ResourceAlreadyExistsException("User", "Email", user.getEmail());

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = repository.findByColumn("email", request.getEmail(), Users.class).orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken, user.getEmail(), user.getName());
    }
}
