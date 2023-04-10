package com.serviceops.assetdiscovery.unit;
import java.sql.Timestamp;

import com.serviceops.assetdiscovery.entity.Credentials;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.rest.CredentialsRest;
import com.serviceops.assetdiscovery.utils.mapper.CredentialsOps;
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

        //customRepository.findByColumn( "ipAddress","127.0.0.1",Credentials.class).get();
      //  assertThat()

    }
    @Test
    void checkCredentialsOps(){
        Credentials credentials = new Credentials();
        credentials.setUsername("");
        credentials.setPassword("");
        credentials.setIpAddress("");
        credentials.setCreatedBy("");
        credentials.setCreatedTime(new Timestamp(new java.util.Date().getTime()));
        credentials.setUpdatedBy("");
        credentials.setUpdatedTime(new Timestamp(new java.util.Date().getTime()));
        credentials.setId(0L);
        credentials.setPassword("123");
        credentials.setUsername("123");
        credentials.setIpAddress("127.0.0.1");

        Credentials credentials2 = new Credentials();
        credentials2.setUsername("");
        credentials2.setPassword("");
        credentials2.setIpAddress("");
        credentials2.setCreatedBy("");
        credentials2.setCreatedTime(new Timestamp(new java.util.Date().getTime()));
        credentials2.setUpdatedBy("");
        credentials2.setUpdatedTime(new Timestamp(new java.util.Date().getTime()));
        credentials2.setId(0L);
        credentials2.setPassword("123kk");
        credentials2.setUsername("123kjk");
        credentials2.setIpAddress("127.0.0.2");
        CredentialsRest credentialsRest = new CredentialsOps(credentials, new CredentialsRest()).entityToRest();
        CredentialsRest credentialsRest1 = new CredentialsOps(credentials2,new CredentialsRest()).entityToRest();
        System.out.println(credentialsRest);
        System.out.println(credentialsRest1);
    }
}
