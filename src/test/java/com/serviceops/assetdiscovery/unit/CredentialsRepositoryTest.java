package com.serviceops.assetdiscovery.unit;

import com.serviceops.assetdiscovery.entity.Credentials;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.Inet4Address;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@SpringBootTest
public class CredentialsRepositoryTest {
    @Autowired
    CustomRepository customRepository;

    @Test
    void addCredentials(){
        Credentials credentials = new Credentials();
        credentials.setPassword("123");
        credentials.setUsername("123");
        credentials.setIpAddress("127.0.0.1");
        customRepository.save(credentials);
        customRepository.findByColumn( "ipAddress","127.0.0.1",Credentials.class).get();
      //  assertThat()

    }
}
