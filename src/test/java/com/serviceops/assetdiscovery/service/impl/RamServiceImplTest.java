package com.serviceops.assetdiscovery.service.impl;

import com.jcraft.jsch.JSchException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RamServiceImplTest {
    @Autowired
private CustomRepository customRepository;
 /*   @Test
    void save() throws JSchException, IOException {
        RamServiceImpl ramService = new RamServiceImpl(customRepository);
        LinuxCommandExecutorManager linuxCommandExecutorManager = new LinuxCommandExecutorManager("10.20.40.238","vishwas","Mind@123",22);
        linuxCommandExecutorManager.fetch();
        ramService.save(1l);
    }*/
}