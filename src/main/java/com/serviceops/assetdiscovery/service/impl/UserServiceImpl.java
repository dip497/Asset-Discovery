package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.Users;
import com.serviceops.assetdiscovery.entity.enums.Role;
import com.serviceops.assetdiscovery.exception.ResourceAlreadyExistsException;
import com.serviceops.assetdiscovery.exception.ResourceNotFoundException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.AuthenticationResponse;
import com.serviceops.assetdiscovery.rest.UsersRest;
import com.serviceops.assetdiscovery.service.interfaces.UsersService;
import com.serviceops.assetdiscovery.utils.mapper.UsersOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UsersService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final CustomRepository customRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(CustomRepository customRepository, AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.customRepository = customRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthenticationResponse register(UsersRest usersRest) {
        Optional<Users> users = customRepository.findByColumn("email", usersRest.getEmail(), Users.class);
        if (users.isEmpty()) {
            Users user = new Users();
            usersRest.setPassword(passwordEncoder.encode(usersRest.getPassword()));
            usersRest.setRole(Role.USER);
            user = new UsersOps(user, usersRest).restToEntity();
            user = customRepository.save(user);
            String jwtToken = jwtService.generateToken(user);
            logger.info("Successfully registered user with email -> {}", user.getEmail());
            return new AuthenticationResponse(jwtToken, user.getEmail(), user.getName());
        } else {
            logger.error("fail to register user with email -> {}", usersRest.getEmail());
            throw new ResourceAlreadyExistsException("User", "Email", usersRest.getEmail());
        }
    }

    @Override
    public AuthenticationResponse authenticate(UsersRest usersRest) {
        Optional<Users> users = customRepository.findByColumn("email", usersRest.getEmail(), Users.class);
        if (users.isPresent()) {
            Users user = users.get();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getId(), usersRest.getPassword()));
            String jwtToken = jwtService.generateToken(user);
            logger.info("Successfully authenticate user with email -> {}", user.getEmail());
            return new AuthenticationResponse(jwtToken, user.getEmail(), user.getName());
        } else {
            throw new ResourceNotFoundException("User", "email", usersRest.getEmail());
        }
    }

    @Override
    public boolean checkForUserInDB(String email) {

        Optional<Users> users = customRepository.findByColumn("email", email, Users.class);

        return users.isPresent();

    }
}
