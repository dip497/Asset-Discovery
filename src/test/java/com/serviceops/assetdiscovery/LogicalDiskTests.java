package com.serviceops.assetdiscovery;

import com.jcraft.jsch.JSchException;
import com.serviceops.assetdiscovery.repository.CustomRepository;
import com.serviceops.assetdiscovery.service.impl.LogicalDiskServiceImpl;
import com.serviceops.assetdiscovery.utils.LinuxCommandExecutorManager;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@SpringBootTest
public class LogicalDiskTests {

    @Autowired
    private CustomRepository customRepository;
    @Test
    public void save() throws JSchException, IOException {
        LogicalDiskServiceImpl logicalDiskService = new LogicalDiskServiceImpl(customRepository);
        LinuxCommandExecutorManager linuxCommandExecutorManager =new LinuxCommandExecutorManager("172.16.12.101","flotomate","admin",22);
        linuxCommandExecutorManager.fetch();
        logicalDiskService.save(1l);
    }


}
