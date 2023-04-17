package com.serviceops.assetdiscovery.service.impl;

import com.serviceops.assetdiscovery.entity.enums.TokenType;
import com.serviceops.assetdiscovery.entity.Tokens;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {
    private final CustomRepository tokenRepository;

    public LogoutService(CustomRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if(authHeader == null || !authHeader.startsWith("Bearer")){
            return;
        }
        jwt = authHeader.substring(7);
        Tokens token = new Tokens();
        token.setToken(jwt);
        token.setTokenType(TokenType.BEARER);
        token.setExpired(true);
        token.setRevoked(true);
        tokenRepository.save(token);
    }
}
