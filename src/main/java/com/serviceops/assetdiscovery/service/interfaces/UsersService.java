package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.AuthenticationResponse;
import com.serviceops.assetdiscovery.rest.UsersRest;

public interface UsersService {

    AuthenticationResponse register(UsersRest usersRest);

    AuthenticationResponse authenticate(UsersRest usersRest);

}
