package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Users;
import com.serviceops.assetdiscovery.entity.enums.Role;
import com.serviceops.assetdiscovery.exception.ResourceAlreadyExistsException;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.AuthenticationRequest;
import com.serviceops.assetdiscovery.rest.AuthenticationResponse;
import com.serviceops.assetdiscovery.rest.RegisterRequest;
import com.serviceops.assetdiscovery.service.interfaces.AuthenticationService;
import com.serviceops.assetdiscovery.service.interfaces.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private final CustomRepository customRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UsersService usersService;

    public AuthenticationServiceImpl(CustomRepository customRepository, PasswordEncoder passwordEncoder,
            JwtService jwtService, AuthenticationManager authenticationManager, UsersService usersService) {
        this.customRepository = customRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.usersService = usersService;
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        Users user = new Users(request.getName(), request.getEmail(),
                passwordEncoder.encode(request.getPassword()), Role.USER);

        if (!usersService.checkForUserInDB(user.getEmail())) {
            customRepository.save(user);
            logger.info("Successfully registered user with email -> {}", user.getEmail());
            String jwtToken = jwtService.generateToken(user);
            return new AuthenticationResponse(jwtToken, user.getEmail(), user.getName());

        } else
            throw new ResourceAlreadyExistsException("User", "Email", user.getId());

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Optional<Users> users = customRepository.findByColumn("email", request.getEmail(), Users.class);
        if (users.isPresent()) {
            Users user = users.get();
            String jwtToken = jwtService.generateToken(user);
            return new AuthenticationResponse(jwtToken, user.getEmail(), user.getName());
        } else {
            throw new ResourceNotFoundException("User", "email", request.getEmail());
        }
    }
}
